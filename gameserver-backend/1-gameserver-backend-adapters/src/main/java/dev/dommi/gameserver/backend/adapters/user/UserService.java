package dev.dommi.gameserver.backend.adapters.user;

import dev.dommi.gameserver.backend.adapters.user.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService implements UserAdapter {

    private static Map<Integer, User> users = new HashMap<>();
    private static AtomicInteger lastId;
    private static UserService instance;

    static {
        users.put(0, new User(0, "Alice", "alice@alice.java"));
        users.put(1, new User(1, "Bob", "bob@bob.java"));
        users.put(2, new User(2, "Carol", "carol@carol.java"));
        users.put(3, new User(3, "Dave", "dave@dave.java"));
        lastId = new AtomicInteger(users.size());
    }

    private UserService() {
    }

    public void save(String name, String email) {
        int id = lastId.incrementAndGet();
        users.put(id, new User(id, name, email));
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public void update(int userId, String name, String email) {
        users.put(userId, new User(userId, name, email));
    }

    public User findById(int userId) {
        return users.get(userId);
    }

    public void delete(int userId) {
        users.remove(userId);
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

}