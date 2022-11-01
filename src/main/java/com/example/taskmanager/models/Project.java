package com.example.taskmanager.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "project")
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 3, max = 50, message = "Project name must be between 3 and 50 characters")
    @NotNull(message = "Project name must not be empty")
    private int name;

    @Column(name = "description")
    @Size(max = 250,  message = "Description must be less than and 250 characters")
    private int description;

    @Column(name = "start")
    private LocalDate start;

    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
}
