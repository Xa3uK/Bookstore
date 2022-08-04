package org.fishbone.jpapractice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.mappers.Mapper;
import org.fishbone.jpapractice.models.Book;
import org.fishbone.jpapractice.models.Person;
import org.fishbone.jpapractice.models.Wishlist;
import org.fishbone.jpapractice.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final PersonDetailsService personDetailsService;
    private final BookService bookService;
    private final Mapper mapper;

    @Autowired
    public WishListService(WishListRepository wishListRepository, PersonDetailsService personDetailsService,
                           BookService bookService, Mapper mapper) {
        this.wishListRepository = wishListRepository;
        this.personDetailsService = personDetailsService;
        this.bookService = bookService;
        this.mapper = mapper;
    }

    public void save(int bookId) {
        String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal()).getUsername();

        Optional<Person> person = personDetailsService.findUserByName(userName);
        Book book = bookService.findById(bookId);

        List<Wishlist> wishlistLists = wishListRepository.findWishlistsByPersonId(person.get().getId());
        boolean isExists = wishlistLists.stream().anyMatch(wish -> wish.getBook().getId() == bookId);
        if (!isExists) {
            wishListRepository.save(new Wishlist(person.get(), book));
        }
    }

    public List<BookDTO> findAll() {
        String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal()).getUsername();

        Optional<Person> person = personDetailsService.findUserByName(userName);
        List<Wishlist> wishlistList = wishListRepository.findWishlistsByPersonId(person.get().getId());

        return wishlistList.stream()
            .map(wish -> mapper.bookToDto(bookService.findById(wish.getBook().getId())))
            .collect(Collectors.toList());
    }

    @Transactional
    public void deleteWishListByUser(int bookId) {
        String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal()).getUsername();

        Optional<Person> person = personDetailsService.findUserByName(userName);
        wishListRepository.deleteWishlistByBook_IdAndPerson_Id(bookId, person.get().getId());
    }

    @Transactional
    public void deleteWishListbyBookId(int bookId){
        wishListRepository.deleteWishlistByBook_Id(bookId);
    }
}
