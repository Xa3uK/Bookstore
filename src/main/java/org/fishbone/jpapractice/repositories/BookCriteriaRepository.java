package org.fishbone.jpapractice.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.Data;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.mappers.Mapper;
import org.fishbone.jpapractice.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

@Repository
public class BookCriteriaRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final Mapper mapper;

    public BookCriteriaRepository(EntityManager entityManager, Mapper mapper) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.mapper = mapper;
    }

    public Page<BookDTO> findAllWithFilters(BookPage bookPage, BookSearchCriteria bookSearchCriteria) {
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        Predicate predicate = getPredicate(bookSearchCriteria, bookRoot);
        criteriaQuery.where(predicate);
        setOrder(bookPage, criteriaQuery, bookRoot);

        TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(bookPage.getPageNumber() * bookPage.getPageSize());
        typedQuery.setMaxResults(bookPage.getPageSize());

        Pageable pageable = getPageable(bookPage);

        long booksCount = getBooksCount(predicate);

        List<BookDTO> bookDTOS = typedQuery.getResultList().stream()
            .map(mapper::bookToDto)
            .collect(Collectors.toList());

        return new PageImpl<>(bookDTOS, pageable, booksCount);
    }

    private long getBooksCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Book> countRoot = countQuery.from(Book.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(BookPage bookPage) {
        Sort sort = Sort.by(bookPage.getSortDirection(), bookPage.getSortBy());
        return PageRequest.of(bookPage.getPageNumber(), bookPage.getPageSize(), sort);
    }

    private void setOrder(BookPage bookPage, CriteriaQuery<Book> criteriaQuery, Root<Book> bookRoot) {
        if (bookPage.getSortDirection().equals(Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(bookRoot.get(bookPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(bookRoot.get(bookPage.getSortBy())));
        }
    }

    private Predicate getPredicate(BookSearchCriteria bookSearchCriteria, Root<Book> bookRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(bookSearchCriteria.getTitle())) {
            predicates.add(criteriaBuilder.like(bookRoot.get("title"), "%" + bookSearchCriteria.getTitle() + "%"));
        }

        if (Objects.nonNull(bookSearchCriteria.getPublisherName())) {
            predicates.add(criteriaBuilder.like(bookRoot.get("publisher").get("name"),
                "%" + bookSearchCriteria.getPublisherName() + "%"));
        }

        if (Objects.nonNull(bookSearchCriteria.getAuthorName())) {
            predicates.add(criteriaBuilder.like(bookRoot.get("author").get("name"),
                "%" + bookSearchCriteria.getAuthorName() + "%"));
        }

        if (Objects.nonNull(bookSearchCriteria.getSubThemeName())) {
            predicates.add(criteriaBuilder.like(bookRoot.get("subTheme").get("name"),
                "%" + bookSearchCriteria.getSubThemeName() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    @Data
    public static class BookPage {
        private int pageNumber = 0;
        private int pageSize = 10;
        private Sort.Direction sortDirection = Direction.ASC;
        private String sortBy = "title";
    }

    @Data
    public static class BookSearchCriteria {
        private String title;
        private String publisherName;
        private String subThemeName;
        private String authorName;
    }
}
