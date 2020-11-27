package com.hexenwerk.tutorial.javafx.crm.service;

import com.hexenwerk.tutorial.javafx.crm.domain.Person;
import com.hexenwerk.tutorial.javafx.crm.repository.UserRepository;
import com.hexenwerk.tutorial.javafx.crm.repository.model.PersonEntity;
import com.hexenwerk.tutorial.javafx.crm.service.mapper.PersonMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final UserRepository userRepository;

    public PersonServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Person save(Person person) {
        return PersonMapper.toPerson(userRepository.save(PersonMapper.toPersonEntity(person)));
    }

    @Override
    public Person update(Person person) {
        return PersonMapper.toPerson(userRepository.save(PersonMapper.toPersonEntity(person)));
    }

    @Override
    public void delete(Person person) {
        userRepository.delete(PersonMapper.toPersonEntity(person));
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteInBatch(List<Person> entities) {
        userRepository.deleteInBatch(entities.stream().map(PersonMapper::toPersonEntity).collect(Collectors.toList()));
    }

    @Override
    public Person findById(String id) {
        return userRepository.findById(id).map(PersonMapper::toPerson).orElse(null);
    }

    @Override
    public List<Person> findAll() {
        return userRepository.findAll().stream().map(PersonMapper::toPerson).collect(Collectors.toList());
    }

    @Override
    public boolean authenticate(String username, String password) {
        PersonEntity user = this.findByEmail(username);
        if (user == null) {
            return false;
        } else {
            return password.equals(user.getPassword());
        }
    }

    @Override
    public PersonEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
