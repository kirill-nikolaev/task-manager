package com.example.taskmanager.controllers;

import com.example.taskmanager.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectsService projectsService;

    @Autowired
    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping
    public String showProjects(Model model) {
        model.addAttribute("projects", projectsService.findAll());
        return "projects/showProjects";
    }

    @GetMapping("/{id}")
    public String showProject(@PathVariable("id") int id,
                              Model model) {

        return "projects/showProject";
    }
}
