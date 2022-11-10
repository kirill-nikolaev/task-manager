package com.example.taskmanager.services;

import com.example.taskmanager.models.Person;
import com.example.taskmanager.models.Project;
import com.example.taskmanager.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final  PeopleService peopleService;

    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository, PersonDetailsService personDetailsService, PeopleService peopleService) {
        this.projectsRepository = projectsRepository;
        this.peopleService = peopleService;
    }

    public List<Project> findAll() {
        return projectsRepository.findAll();
    }

    public Optional<Project> findByName(String name) {
        return projectsRepository.findByName(name).stream().findAny();
    }

    @Transactional
    public void registerProject(Project project, String manager) {
        project.setStart(LocalDate.now());
        Person person = peopleService.findByUsername(manager).stream().findAny().orElse(null);
        project.setPerson(person);
        projectsRepository.save(project);
    }
}
