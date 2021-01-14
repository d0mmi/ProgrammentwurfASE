package dev.dommi.gameserver.backend.adapter.api.user;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTests {

    @Test
    public void convertToUserFromTest() {
        UserEntity entity = new UserEntity(1, "TestUser", "test@example.com", new RankVO("User", 1));
        User user = UserService.convertToUserFrom(entity);
        assertNotNull(user);
        assertEquals(entity.id, user.id);
        assertEquals(entity.name, user.name);
        assertEquals(entity.email, user.email);
        assertEquals(entity.rank.level, user.level);
    }

    @Test
    public void convertToUserCollectionFromTest() {
        Collection<UserEntity> entities = createEntities();
        Collection<User> users = UserService.convertToUserCollectionFrom(entities);

        for (int i = 0; i < entities.size(); i++) {
            User user = (User) users.toArray()[i];
            UserEntity entity = (UserEntity) entities.toArray()[i];
            assertNotNull(user);
            assertEquals(entity.id, user.id);
            assertEquals(entity.name, user.name);
            assertEquals(entity.email, user.email);
            assertEquals(entity.rank.level, user.level);
        }

    }

    private Collection<UserEntity> createEntities() {
        List<UserEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            entities.add(new UserEntity(i, "TestUser" + i, "test" + i + "@example.com", new RankVO("User", 1)));
        }
        return entities;
    }
}
