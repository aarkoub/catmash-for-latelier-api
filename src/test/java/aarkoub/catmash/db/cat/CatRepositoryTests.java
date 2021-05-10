package aarkoub.catmash.db.cat;

import aarkoub.catmash.domain.cat.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureTestEntityManager
@Transactional
public class CatRepositoryTests {

    @Autowired
    private ICatRepository catRepository ;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAdd() {
        Cat cat = new Cat("fake_url", 0);
        long l = catRepository.add(cat);
        Cat ret_cat = testEntityManager.find(Cat.class, l);
        Assertions.assertEquals(cat.getId(), ret_cat.getId());
    }

    @Test
    public void testIncreaseVote() throws Exception {
        Cat cat = new Cat("fake_url", 5);
        long id = testEntityManager.persist(cat).getId();
        catRepository.increaseVote(id);
        Cat ret_cat = testEntityManager.find(Cat.class, id);
        Assertions.assertEquals(6, ret_cat.getNbVotes());
    }

    @Test
    public void testDecreaseVote() throws Exception {
        Cat cat = new Cat("fake_url", 5);
        long id = testEntityManager.persist(cat).getId();
        catRepository.decreaseVote(id);
        Cat ret_cat = testEntityManager.find(Cat.class, id);
        Assertions.assertEquals(4, ret_cat.getNbVotes());
    }

    @Test
    public void testGet() {
        Cat cat_1 = new Cat("fake_url1", 0);
        Cat cat_2 = new Cat("fake_url2", 0);
        long id_1 = testEntityManager.persist(cat_1).getId();
        long id_2 = testEntityManager.persist(cat_2).getId();

        List<Cat> expectedCats = new ArrayList<>();
        expectedCats.add(cat_1);
        expectedCats.add(cat_2);

        Assertions.assertEquals(expectedCats, catRepository.getAll());
    }

}
