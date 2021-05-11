package aarkoub.catmash.domain.user;

import aarkoub.catmash.db.user.IUserRepository;
import aarkoub.catmash.db.user.UserNotFoundException;
import aarkoub.catmash.domain.user.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

@SpringBootTest
public class UserServiceTest {

    @MockBean

    IUserRepository mockRepository;

    @Autowired
    IUserService userService;

    @Test
    public void testAdd() throws UserNotFoundException {

        Mockito.when(mockRepository.add()).thenReturn(new User(UUID.randomUUID()));

        User u = userService.retrieveUser(null);

        Mockito.when(mockRepository.find(u.getId())).thenReturn(u);
        Assertions.assertEquals(u, userService.retrieveUser(u.getId()));

        Mockito.when(mockRepository.find(UUID.randomUUID())).thenThrow(UserNotFoundException.class);
        Assertions.assertEquals(User.class, userService.retrieveUser(u.getId()).getClass());

    }

}
