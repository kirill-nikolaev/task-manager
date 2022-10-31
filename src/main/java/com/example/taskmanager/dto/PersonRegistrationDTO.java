package com.example.taskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class PersonRegistrationDTO {

    @Size(min = 4, max = 30, message = "Username must be between 4 and 30 characters")
    @NotNull(message = "Username cannot be empty")
    private String username;

    @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
    private String password;

    private String repeatPassword;

    @Size(max = 30, message = "First name must be less than 30 characters")
    @NotNull(message = "First name cannot be empty")
    private String firstName;

    @Size(max = 30, message = "First name must be less than 30 characters")
    @NotNull(message = "First name cannot be empty")
    private String lastName;

    @Email(message = "Email not valid")
    @NotNull(message = "Email cannot be empty")
    private String email;

}
