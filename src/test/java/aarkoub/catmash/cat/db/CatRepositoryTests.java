package aarkoub.catmash.cat.db;

import aarkoub.catmash.cat.domain.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EnableJpaRepositories(basePackages = {"aarkoub.catmash"})
@AutoConfigureTestEntityManager
@Transactional
public class CatRepositoryTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CatRepositorySQL catRepository = new CatRepositorySQL();

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAddCat() {
        Cat cat = new Cat("fake_url", 0);
        long l = catRepository.addCat(cat);
        Cat ret_cat = testEntityManager.find(Cat.class, l);
        Assertions.assertEquals(cat.getId(), ret_cat.getId());
    }

    @Test
    public void testVoteForCat() throws Exception {
        Cat cat = new Cat("fake_url", 5);
        long id = testEntityManager.persist(cat).getId();
        catRepository.voteForCat(id);
        Cat ret_cat = testEntityManager.find(Cat.class, id);
        Assertions.assertEquals(6, ret_cat.getNbVotes());
    }

    @Test
    public void testgetAllCats() {
        Cat cat_1 = new Cat("fake_url1", 0);
        Cat cat_2 = new Cat("fake_url2", 0);
        long id_1 = testEntityManager.persist(cat_1).getId();
        long id_2 = testEntityManager.persist(cat_2).getId();

        List<Cat> expectedCats = new ArrayList<>();
        expectedCats.add(cat_1);
        expectedCats.add(cat_2);

        Assertions.assertEquals(expectedCats, catRepository.getAllCats());
    }

}
