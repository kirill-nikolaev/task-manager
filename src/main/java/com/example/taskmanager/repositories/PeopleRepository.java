package com.example.taskmanager.repositories;

import com.example.taskmanager.models.Person;
import com.example.taskmanager.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    List<Person> findByUsername(String username);

    List<Person> findByEmail(String email);

    List<Person> findByRole(Role role);
}
