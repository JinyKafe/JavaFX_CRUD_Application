package com.hexenwerk.tutorial.javafx.crm.service;

import java.util.List;

public interface CrudService<T, ID> {

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(ID id);

    void deleteInBatch(List<T> entities);

    T findById(ID id);

    List<T> findAll();
}
