package ru.ifmo.uml.dao;



import ru.ifmo.uml.domain.Entity;

import java.util.List;



public interface Dao<T extends Entity> {

    void insert(T o);

    T get(long id);

    List<T> getAll();

    void update(T o);

    void remove(long id);
}
