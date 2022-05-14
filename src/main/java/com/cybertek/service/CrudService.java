package com.cybertek.service;

import java.util.List;

public interface CrudService<T, ID> {
    // save
    T save(T obj);

    // findByID
    T findByID(ID id);

    // return list
    List<T> findAll();

    // delete
    void delete(T obj);
    void deleteByID(ID id);

    // update
    void update(T obj);
}
