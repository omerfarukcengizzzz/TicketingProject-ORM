package com.cybertek.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    // save
    T save(ID id, T obj) {
        map.put(id, obj);
        return obj;
    }

    // findAll
    List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    // findByID
    T findByID(ID id) {
        return map.get(id);
    }

    // delete
    void deleteByID(ID id) {
        map.remove(id);
    }

    void delete(T obj) {
        map.entrySet().removeIf(ob -> ob.getValue().equals(obj));
    }

    // update
    void update(ID id, T obj) {
        /*  since, map.put() method checks if the given item is on the map or not
            (if the item is there, it deletes and then puts the new one), we don't need to delete it by ourselves.
        */
        // delete(obj);
        save(id, obj);
    }

}
