package com.example.taskmanager.util;

import com.example.taskmanager.dto.ProjectDTO;
import com.example.taskmanager.models.Person;
import com.example.taskmanager.models.Project;
import com.example.taskmanager.services.PeopleService;
import com.example.taskmanager.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProjectDTOValidator implements Validator {

    private final ProjectsService projectsService;
    private final PeopleService peopleService;

    @Autowired
    public ProjectDTOValidator(ProjectsService projectsService, PeopleService peopleService) {
        this.projectsService = projectsService;
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProjectDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProjectDTO project = (ProjectDTO) target;

        Optional<Project> projectFromDb = projectsService.findByName(project.getName());

        if (projectFromDb.isPresent()) {
            errors.rejectValue("name", "", "Project with same name already exist");
        }

        Optional<Person> managerFromDb = peopleService.findByUsername(project.getManagerName());

        if (managerFromDb.isEmpty()) {
            errors.rejectValue("managerName", "", "Manager not found");
        }
    }
}
