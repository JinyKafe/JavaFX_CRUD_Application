package com.hexenwerk.tutorial.javafx.crm.service.mapper;

import com.hexenwerk.tutorial.javafx.crm.domain.Person;
import com.hexenwerk.tutorial.javafx.crm.repository.model.PersonEntity;

public class PersonMapper {

    public static Person toPerson(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .email(personEntity.getEmail())
                .password(personEntity.getPassword())
                .role(personEntity.getRole())
                .gender(personEntity.getGender())
                .birthDay(personEntity.getDob())
                .build();
    }


    public static PersonEntity toPersonEntity(Person person) {
        return PersonEntity.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .dob(person.getBirthDay())
                .gender(person.getGender())
                .role(person.getRole())
                .email(person.getEmail())
                .password(person.getPassword())
                .build();
    }
}
