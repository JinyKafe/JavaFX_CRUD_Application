package com.hexenwerk.tutorial.javafx.crm.repository.model;

import com.hexenwerk.tutorial.javafx.crm.domain.GenderType;
import com.hexenwerk.tutorial.javafx.crm.domain.RoleType;

import java.time.LocalDate;

public class UserEntityBuilder {

    private String id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    private GenderType gender;

    private RoleType role;

    private String email;

    private String password;

    public UserEntityBuilder id(String id) {
        this.id = id;
        return this;
    }

    public UserEntityBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserEntityBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserEntityBuilder dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public UserEntityBuilder dob(long epochDay) {
        this.dob = LocalDate.ofEpochDay(epochDay);
        return this;
    }

    public UserEntityBuilder gender(GenderType gender) {
        this.gender = gender;
        return this;
    }

    public UserEntityBuilder role(RoleType role) {
        this.role = role;
        return this;
    }

    public UserEntityBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserEntityBuilder password(String password) {
        this.password = password;
        return this;
    }

    public PersonEntity build() {
        PersonEntity user = new PersonEntity();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDob(dob);
        user.setGender(gender);
        user.setRole(role);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
