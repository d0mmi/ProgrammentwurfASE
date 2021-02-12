package dev.dommi.gameserver.backend.adapter.database;

import java.sql.SQLException;
import java.util.Collection;

public interface DatabaseController<T> {

    void create(T value) throws SQLException;
    void update(T value) throws SQLException;
    void delete(int value) throws SQLException;
    T findById(int value) throws SQLException;
    Collection<T> findAll() throws SQLException;

}
