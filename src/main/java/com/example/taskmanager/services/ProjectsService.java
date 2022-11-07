package com.example.taskmanager.services;

import com.example.taskmanager.models.Project;
import com.example.taskmanager.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public List<Project> findAll() {
        return projectsRepository.findAll();
    }
}
