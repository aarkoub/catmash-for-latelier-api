package aarkoub.catmash.cat.db.importation;

import aarkoub.catmash.cat.db.CatRepositorySQL;
import aarkoub.catmash.cat.db.ICatRepository;
import aarkoub.catmash.cat.domain.Cat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;


@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureTestEntityManager
@Transactional
public class CatDataLoaderTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CatDataLoader catDataLoader ;

    @Test
    public void testImportCatFromJSON() throws IOException {
        String filepath = "src/test/resources/test/db/importation/fake_data.json";
        catDataLoader.importCatsFromJSONToDatabase(filepath);
        Query q = testEntityManager.getEntityManager().createNativeQuery("Select * from CAT");
        Assertions.assertEquals(2,  q.getResultList().size());
    }

}
