package com.hexenwerk.tutorial.javafx.crm.repository;

import com.hexenwerk.tutorial.javafx.crm.repository.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<PersonEntity, String> {

    PersonEntity findByEmail(String email);
}
