package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import dev.dommi.gameserver.backend.plugin.database.rank.Rank;
import dev.dommi.gameserver.backend.plugin.database.rank.RankController;
import dev.dommi.gameserver.backend.plugin.database.user.User;
import dev.dommi.gameserver.backend.plugin.database.user.UserController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRepositoryImplTests {

    @Test
    public void convertToUserEntityFromTest() throws SQLException {

        UserController userController = mock(UserController.class);
        RankController rankController = mock(RankController.class);
        when(rankController.getRankFrom(anyInt())).thenReturn(new Rank("User", 1));

        User user = new User(1, "TestUser", "test@example.com", "pw");
        UserEntity entity = new UserRepositoryImpl(userController, rankController).convertToUserEntityFrom(user);

        assertNotNull(entity);
        assertEquals(user.id, entity.getId());
        assertEquals(user.name, entity.getName());
        assertEquals(user.email, entity.getEmail());
    }

    @Test
    public void convertToUserEntityCollectionFromTest() throws SQLException {
        UserController userController = mock(UserController.class);
        RankController rankController = mock(RankController.class);
        when(rankController.getRankFrom(anyInt())).thenReturn(new Rank("User", 1));

        Collection<User> users = createUsers();
        Collection<UserEntity> entities = new UserRepositoryImpl(userController, rankController).convertToUserEntityCollectionFrom(users);

        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.toArray()[i];
            UserEntity entity = (UserEntity) entities.toArray()[i];

            assertNotNull(entity);
            assertEquals(user.id, entity.getId());
            assertEquals(user.name, entity.getName());
            assertEquals(user.email, entity.getEmail());
        }

    }

    private Collection<User> createUsers() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "TestUser" + i, "test" + i + "@example.com", "pw"));
        }
        return users;
    }

}
