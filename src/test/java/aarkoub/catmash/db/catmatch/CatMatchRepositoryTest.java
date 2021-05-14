package aarkoub.catmash.db.catmatch;

import aarkoub.catmash.db.cat.ICatRepository;
import aarkoub.catmash.db.user.IUserRepository;
import aarkoub.catmash.domain.cat.Cat;
import aarkoub.catmash.domain.catmatch.CatMatch;
import aarkoub.catmash.domain.user.User;
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
public class CatMatchRepositoryTest {

    @Autowired
    private ICatMatchRepository catMatchRepository ;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICatRepository catRepository ;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAddTest(){
        User user = testEntityManager.persist(new User());
        Cat cat_1 = testEntityManager.persist(new Cat("fake_url_1"));
        Cat cat_2  = testEntityManager.persist(new Cat("fake_url_2"));
        catMatchRepository.add(user, cat_1, cat_2);
        Query q = testEntityManager.getEntityManager().createNativeQuery("select * from cat_match");
        Assertions.assertEquals(1,q.getResultList().size());
    }

    @Test
    public void testFindTest(){
        User user = testEntityManager.persist(new User());
        Cat cat_1 = testEntityManager.persist(new Cat("fake_url_1"));
        Cat cat_2  = testEntityManager.persist(new Cat("fake_url_2"));
        CatMatch cm = new CatMatch(user, cat_1, cat_2, cat_2);
        testEntityManager.persist(cm);
        try {
            CatMatch resultCm = catMatchRepository.find(user.getId(), cat_1.getId(), cat_2.getId());
            Assertions.assertEquals(cm, resultCm);
        } catch (CatMatchNotFoundException e) {
            e.printStackTrace();
        }

        Assertions.assertThrows(CatMatchNotFoundException.class,
                ()->{catMatchRepository.find(user.getId(), cat_1.getId(), cat_1.getId());});
    }

    @Test
    public void testChangeVote(){
        User user = testEntityManager.persist(new User());
        Cat cat_1 = testEntityManager.persist(new Cat("fake_url_1"));
        Cat cat_2  = testEntityManager.persist(new Cat("fake_url_2"));
        CatMatch cm = new CatMatch(user, cat_1, cat_2, cat_2);
        testEntityManager.persist(cm);
        try {
            CatMatch resultCm = catMatchRepository.changeVote(user.getId(), cat_1.getId(), cat_2.getId(), cat_1.getId());
            Assertions.assertEquals(cm.getCat1().getId(), resultCm.getCatVoted().getId());
        } catch (CatMatchNotFoundException e) {
            e.printStackTrace();
        }
    }

}
