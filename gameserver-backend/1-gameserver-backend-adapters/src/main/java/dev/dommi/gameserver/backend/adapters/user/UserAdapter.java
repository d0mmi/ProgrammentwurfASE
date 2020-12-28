package dev.dommi.gameserver.backend.adapters.user;

import java.util.Collection;

public interface UserAdapter {
    public void save(String name, String email);

    public Collection<User> getAll();

    public void update(int userId, String name, String email);

    public User findById(int userId);

    public void delete(int userId);
}
