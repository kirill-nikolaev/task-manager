package com.example.taskmanager.repositories;

import com.example.taskmanager.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Integer> {

    List<Project> findByName(String name);
}
