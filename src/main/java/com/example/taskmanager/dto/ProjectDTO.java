package com.example.taskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {
    @Size(min = 3, max = 50, message = "Project name must be between 3 and 50 characters")
    @NotNull(message = "Project name must not be empty")
    private String name;

    @Size(max = 250,  message = "Description must be less than and 250 characters")
    private String description;

    private String managerName;
}
