package com.hexenwerk.tutorial.javafx.crm.domain.builder;

import com.hexenwerk.tutorial.javafx.crm.domain.GenderType;
import com.hexenwerk.tutorial.javafx.crm.domain.Person;
import com.hexenwerk.tutorial.javafx.crm.domain.RoleType;

import java.time.LocalDate;

public class PersonBuilder {
    private String id;

    private String firstName;

    private String lastName;

    private LocalDate birthDay;

    private GenderType gender;

    private RoleType role;

    private String email;

    private String password;

    public PersonBuilder id(String id) {
        this.id = id;
        return this;
    }

    public PersonBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder birthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public PersonBuilder gender(GenderType gender) {
        this.gender = gender;
        return this;
    }

    public PersonBuilder role(RoleType role) {
        this.role = role;
        return this;
    }

    public PersonBuilder email(String email) {
        this.email = email;
        return this;
    }

    public PersonBuilder password(String password) {
        this.password = password;
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setBirthDay(birthDay);
        person.setEmail(email);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setGender(gender);
        person.setId(id);
        person.setRole(role);
        person.setPassword(password);
        return person;
    }

    public Person update(Person person) {
        person.setBirthDay(birthDay);
        person.setEmail(email);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setGender(gender);
        person.setId(id);
        person.setRole(role);
        person.setPassword(password);
        return person;
    }
}
