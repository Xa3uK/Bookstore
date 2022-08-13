package org.fishbone.jpapractice.utils;

import org.fishbone.jpapractice.models.Person;
import org.fishbone.jpapractice.services.PersonDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    PersonDetailsService personDetailsService;

    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDetailsService.findUserByName(person.getUserName()).isPresent()) {
            errors.rejectValue("userName", "400",
                "User with name '" + person.getUserName() + "' already exists");
        }
    }
}
