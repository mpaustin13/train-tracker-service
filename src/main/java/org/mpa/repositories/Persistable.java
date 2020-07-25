package org.mpa.repositories;

public interface Persistable<T> {

    T getById(T obj);
    void add(T obj);
    void update(T obj);
    void delete(T obj);

}
