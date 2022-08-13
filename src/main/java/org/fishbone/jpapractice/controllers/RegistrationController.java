package org.fishbone.jpapractice.controllers;

import javax.validation.Valid;
import org.fishbone.jpapractice.models.Person;
import org.fishbone.jpapractice.services.PersonDetailsService;
import org.fishbone.jpapractice.utils.PersonValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private final PersonDetailsService personDetailsService;
    private final PersonValidator personValidator;

    public RegistrationController(PersonDetailsService personDetailsService, PersonValidator personValidator) {
        this.personDetailsService = personDetailsService;
        this.personValidator = personValidator;
    }

    @PostMapping("/reg")
    public String register(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        LOGGER.debug("Start: register()");

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        personDetailsService.register(person);

        LOGGER.debug("End: register()");
        return "redirect:/auth/login";
    }
}
