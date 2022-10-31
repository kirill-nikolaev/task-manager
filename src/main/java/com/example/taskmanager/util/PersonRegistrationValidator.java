package com.example.taskmanager.util;

import com.example.taskmanager.dto.PersonRegistrationDTO;
import com.example.taskmanager.models.Person;
import com.example.taskmanager.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonRegistrationValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonRegistrationValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PersonRegistrationDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonRegistrationDTO registeringPerson = (PersonRegistrationDTO) target;

        Optional<Person> personFromDb = peopleService.findByUsername(registeringPerson.getUsername());

        if (personFromDb.isPresent())
            errors.rejectValue("username", "", "User already exist");

        personFromDb = peopleService.findByEmail(registeringPerson.getEmail());

        if (personFromDb.isPresent())
            errors.rejectValue("email", "", "Email already exist");

        if (!registeringPerson.getPassword().equals(registeringPerson.getRepeatPassword())) {
            errors.rejectValue("repeatPassword", "", "Passwords do not match");
        }
    }
}
