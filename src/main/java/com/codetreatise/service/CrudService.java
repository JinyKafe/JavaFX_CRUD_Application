package com.codetreatise.service;

import java.util.List;

/**
 * @author Ram Alapure
 * @since 05-04-2017
 */

public interface CrudService<T extends Object>
{

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(Long id);

    void deleteInBatch(List<T> entities);

    T findById(Long id);

    List<T> findAll();
}
