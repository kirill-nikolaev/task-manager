package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.PersonRegistrationDTO;
import com.example.taskmanager.models.Person;
import com.example.taskmanager.services.PeopleService;
import com.example.taskmanager.util.PersonRegistrationValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private final PersonRegistrationValidator personRegistrationValidator;
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;


    @Autowired
    public AuthenticationController(PersonRegistrationValidator personRegistrationValidator, PeopleService peopleService, ModelMapper modelMapper) {
        this.personRegistrationValidator = personRegistrationValidator;
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/registration")
    public String registerForm(@ModelAttribute("person") PersonRegistrationDTO personRegistrationDTO) {

        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("person") @Valid PersonRegistrationDTO personRegistrationDTO,
                           BindingResult bindingResult) {
        personRegistrationValidator.validate(personRegistrationDTO, bindingResult);

        if (bindingResult.hasErrors())
            return "registration";

        Person person = mapToPerson(personRegistrationDTO);
        peopleService.register(person);

        return "redirect:/users";
    }

    private Person mapToPerson(PersonRegistrationDTO personRegistrationDTO) {
        return modelMapper.map(personRegistrationDTO, Person.class);
    }
}
