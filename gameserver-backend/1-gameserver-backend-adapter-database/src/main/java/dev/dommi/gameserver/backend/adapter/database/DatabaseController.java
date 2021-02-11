package dev.dommi.gameserver.backend.adapter.database;

import java.sql.SQLException;
import java.util.Collection;

public interface DatabaseController<T> {

    public void create(T value) throws SQLException;
    public void update(T value) throws SQLException;
    public void delete(int value) throws SQLException;
    public T findById(int value) throws SQLException;
    public Collection<T> findAll() throws SQLException;

}
