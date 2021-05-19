package dev.dommi.gameserver.backend.adapter.database;

import java.util.Collection;

public interface DatabaseController<T> {

    boolean create(T value);
    boolean update(T value);
    boolean delete(int value);
    T findById(int value);
    Collection<T> findAll();

}
