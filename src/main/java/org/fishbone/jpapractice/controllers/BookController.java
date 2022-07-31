package org.fishbone.jpapractice.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.mappers.Mapper;
import org.fishbone.jpapractice.repositories.BookCriteriaRepository.BookPage;
import org.fishbone.jpapractice.repositories.BookCriteriaRepository.BookSearchCriteria;
import org.fishbone.jpapractice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookController {

    BookService bookService;
    Mapper mapper;

    @Autowired
    public BookController(BookService bookService, Mapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping()
    public String getAllBooks(Model model,
                              @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                              @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                              @RequestParam(required = false, defaultValue = "title") String sortBy,
                              @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String publisherName,
                              @RequestParam(required = false) String subThemeName,
                              @RequestParam(required = false) String authorName) {

        Page<BookDTO> bookPage = bookService.getAllWithFilter(new BookPage(pageNumber, pageSize, sortDirection,
                sortBy),
            new BookSearchCriteria(title, publisherName, subThemeName, authorName));

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("title", title);
        model.addAttribute("publisherName",publisherName);
        model.addAttribute("subThemeName", subThemeName);
        model.addAttribute("authorName", authorName);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "books_main";
    }

    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @PostMapping("/create")
    public String addBook(
        @RequestParam String title,
        @RequestParam Integer price,
        @RequestParam String publisherName,
        @RequestParam String languageName,
        @RequestParam String authorName,
        @RequestParam String subThemeName,
        @RequestParam String coverType) {

        bookService.addBook(mapper.dtoToBook(
            new BookDTO(title, price, publisherName, languageName, subThemeName, coverType, authorName)));

        return "redirect:/books";
    }

    @GetMapping("/id")
    public BookDTO findById(int id) {
        return mapper.bookToDto(bookService.findById(id));
    }

    @PostMapping("/delete")
    public String deleteBookById(@RequestParam int id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }
}
