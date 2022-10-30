package com.example.taskmanager.services;

import com.example.taskmanager.models.Person;
import com.example.taskmanager.models.Role;
import com.example.taskmanager.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void changeRoleById(int id, Role role) {
        Person person = peopleRepository.getById(id);
        person.setRole(role);
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }
}
