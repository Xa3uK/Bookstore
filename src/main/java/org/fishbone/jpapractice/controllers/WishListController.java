package org.fishbone.jpapractice.controllers;

import org.fishbone.jpapractice.services.WishListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wishlist")
public class WishListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WishListController.class);

    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/{id}/add")
    public String add(@PathVariable("id") int id) {
        LOGGER.debug("Start: wishlistAdd()");

        wishListService.save(id);

        LOGGER.debug("End: wishlistAdd()");
        return "redirect:/books";
    }

    @GetMapping
    public String getAll(Model model) {
        LOGGER.debug("Start: wishlistGetAll()");

        model.addAttribute("books", wishListService.findAll());

        LOGGER.debug("End: wishlistGetAll()");
        return "my_wishlist";
    }

    @GetMapping("{id}/delete")
    public String delete(Model model, @PathVariable("id") int bookId) {
        LOGGER.debug("Start: wishlistDelete()");

        wishListService.deleteWishListByBookId(bookId);
        model.addAttribute("books", wishListService.findAll());

        LOGGER.debug("End: wishlistDelete()");
        return "my_wishlist";
    }
}
