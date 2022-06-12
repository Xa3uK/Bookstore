package org.fishbone.jpapractice.services;

import java.util.List;
import java.util.Optional;
import org.fishbone.jpapractice.models.Book;
import org.fishbone.jpapractice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BookService {

    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> findAll(){
        return bookRepository.findAll();
    }


    public Book findById(int id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void deleteBookById(int id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public void addBook(Book book){
        bookRepository.save(book);
    }
}
