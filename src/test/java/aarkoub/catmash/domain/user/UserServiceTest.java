package aarkoub.catmash.domain.user;

import aarkoub.catmash.db.user.IUserRepository;
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
    public void testAddUser(){
        UUID id = UUID.randomUUID();
        Mockito.when(mockRepository.addUser()).thenReturn(id);
        Assertions.assertEquals(id, userService.addUser());
    }

}
