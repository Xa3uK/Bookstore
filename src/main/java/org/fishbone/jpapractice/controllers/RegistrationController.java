package org.fishbone.jpapractice.controllers;

import javax.validation.Valid;
import org.fishbone.jpapractice.models.Person;
import org.fishbone.jpapractice.services.PersonDetailsService;
import org.fishbone.jpapractice.services.RegistrationService;
import org.fishbone.jpapractice.utils.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;

    public RegistrationController(RegistrationService registrationService, PersonValidator personValidator) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
    }

    @PostMapping("/reg")
    public String register(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        registrationService.register(person);
        return "redirect:/auth/login";
    }
}
