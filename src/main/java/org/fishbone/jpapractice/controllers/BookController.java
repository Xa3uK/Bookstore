package org.fishbone.jpapractice.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.validation.Valid;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.mappers.Mapper;
import org.fishbone.jpapractice.repositories.BookCriteriaRepository.BookPage;
import org.fishbone.jpapractice.repositories.BookCriteriaRepository.BookSearchCriteria;
import org.fishbone.jpapractice.services.BookService;
import org.fishbone.jpapractice.services.WishListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;
    private final WishListService wishListService;
    private final Mapper mapper;

    public BookController(BookService bookService, WishListService wishListService, Mapper mapper) {
        this.bookService = bookService;
        this.wishListService = wishListService;
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
        LOGGER.debug("Start: getAllBooks()");

        Page<BookDTO> bookPage = bookService.getAllWithFilter(new BookPage(pageNumber, pageSize, sortDirection,
                sortBy),
            new BookSearchCriteria(title, publisherName, subThemeName, authorName));

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("title", title);
        model.addAttribute("publisherName", publisherName);
        model.addAttribute("subThemeName", subThemeName);
        model.addAttribute("authorName", authorName);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        LOGGER.debug("End: getAllBooks()");
        return "books_main";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("BookDTO", new BookDTO());
        return "create";
    }

    @PostMapping("/create")
    public String addBook(@ModelAttribute("BookDTO") @Valid BookDTO bookDTO, BindingResult bindingResult) {
        LOGGER.debug("Start: addBook()");

        if (bindingResult.hasErrors()) {
            return "/create";
        }

        bookService.addBook(mapper.dtoToBook(bookDTO));

        LOGGER.debug("End: addBook()");
        return "redirect:/books";
    }

    @GetMapping("/id")
    public BookDTO findById(int id) {
        return mapper.bookToDto(bookService.findById(id));
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        LOGGER.debug("Start: deleteBook()");

        bookService.deleteBookById(id);

        LOGGER.debug("End: deleteBook()");
        return "redirect:/books";
    }
}
