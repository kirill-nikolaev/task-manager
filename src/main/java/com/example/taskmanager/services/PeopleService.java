package com.example.taskmanager.services;

import com.example.taskmanager.models.Person;
import com.example.taskmanager.models.Role;
import com.example.taskmanager.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
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

    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username).stream().findAny();
    }

    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email).stream().findAny();
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(Role.ROLE_GUEST);
        peopleRepository.save(person);
    }

    public List<Person> findByRole(Role role) {
        return peopleRepository.findByRole(role);
    }
}
