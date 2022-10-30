package com.example.taskmanager.controllers;

import com.example.taskmanager.models.Person;
import com.example.taskmanager.models.Role;
import com.example.taskmanager.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final PeopleService peopleService;

    @Autowired
    public UsersController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "users/showUsers";
    }

    @GetMapping("/{id}")
    public String showOneUser(@PathVariable("id")int id,
                              Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = peopleService.findById(id);

        model.addAttribute("username", username);
        model.addAttribute("roles", Role.values());
        model.addAttribute("person", person);

        return "users/showUser";
    }

    @PatchMapping("/{id}")
    public String changeRole(@PathVariable("id")int id,
                             @RequestParam("role")String roleValue) {
        Role role = Role.valueOf(roleValue);

        peopleService.changeRoleById(id, role);

        return "redirect:/users/" + id;
    }
}
