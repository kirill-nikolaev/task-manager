package com.example.taskmanager.config;


import com.example.taskmanager.security.PersonAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonAuthenticationProvider personAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Autowired
    public SecurityConfig(PersonAuthenticationProvider personAuthenticationProvider) {
        this.personAuthenticationProvider = personAuthenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(personAuthenticationProvider);
    }
}
