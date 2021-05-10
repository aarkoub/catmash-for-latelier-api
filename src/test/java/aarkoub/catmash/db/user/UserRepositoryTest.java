package aarkoub.catmash.db.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureTestEntityManager
@Transactional
public class UserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void addUserTest(){
        userRepository.add();
        Query q = testEntityManager.getEntityManager().createNativeQuery("select * from user");
        Assertions.assertEquals(1,q.getResultList().size());
    }
}
