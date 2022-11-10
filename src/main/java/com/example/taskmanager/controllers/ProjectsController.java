package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.ProjectDTO;
import com.example.taskmanager.models.Person;
import com.example.taskmanager.models.Project;
import com.example.taskmanager.models.Role;
import com.example.taskmanager.services.PeopleService;
import com.example.taskmanager.services.ProjectsService;
import com.example.taskmanager.util.ProjectDTOValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectsService projectsService;
    private final ProjectDTOValidator projectDTOValidator;
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectsController(ProjectsService projectsService, ProjectDTOValidator projectDTOValidator, PeopleService peopleService, ModelMapper modelMapper) {
        this.projectsService = projectsService;
        this.projectDTOValidator = projectDTOValidator;
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
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

    @GetMapping("/new")
    public String newProject(@ModelAttribute("project") ProjectDTO projectDTO,
                             Model model) {
        List<Person> managers = peopleService.findByRole(Role.ROLE_MANAGER);
        model.addAttribute("managers", managers);
        return "projects/newProject";
    }

    @PostMapping()
    public String createProject(@ModelAttribute("project") @Valid ProjectDTO projectDTO,
                                BindingResult bindingResult,
                                Model model) {
        projectDTOValidator.validate(projectDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            List<Person> managers = peopleService.findByRole(Role.ROLE_MANAGER);
            model.addAttribute("managers", managers);
            return "projects/newProject";
        }

        Project project = mapToProject(projectDTO);
        projectsService.registerProject(project, projectDTO.getManagerName());

        return "redirect:/projects";
    }

    private Project mapToProject(ProjectDTO projectDTO) {
        return modelMapper.map(projectDTO, Project.class);
    }
}
