package com.hexenwerk.tutorial.javafx.crm.service;


import com.hexenwerk.tutorial.javafx.crm.domain.Person;
import com.hexenwerk.tutorial.javafx.crm.repository.model.PersonEntity;

public interface PersonService extends CrudService<Person, String> {

    boolean authenticate(String email, String password);

    PersonEntity findByEmail(String email);

}
