package aarkoub.catmash;

import static org.hamcrest.CoreMatchers.*;

import aarkoub.catmash.domain.cat.Cat;
import aarkoub.catmash.domain.catmatch.CatMatch;
import aarkoub.catmash.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureTestEntityManager
@Transactional
class APITests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void getAllCats() throws Exception {
        testEntityManager.persist(new Cat("fake_url", 0));
        testEntityManager.persist(new Cat("fake_url", 0));
        mockMvc.perform(get("/cats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    void retrieveUser() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(cookie().exists("userId"));

        UUID id = testEntityManager.persist(new User()).getId();
        Cookie cookie = new Cookie("userId", id.toString());
        mockMvc.perform(get("/user").cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(cookie().value("userId", id.toString()));
    }

    @Test
    void generateMatch() throws Exception {

        Cat cat1 = testEntityManager.persist(new Cat("fake_url", 5));
        Cat cat2 = testEntityManager.persist(new Cat("fake_url", 3));
        UUID id = testEntityManager.persist(new User()).getId();
        Cookie cookie = new Cookie("userId", id.toString());

        mockMvc.perform(get("/cats/match").cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id", is(id.toString())))
                .andExpect(jsonPath("$.cat1.id", anyOf(is(Integer.parseInt(Long.toString(cat1.getId())))
                        , is(Integer.parseInt(Long.toString(cat2.getId()))))))
                .andExpect(jsonPath("$.cat2.id", anyOf(is(Integer.parseInt(Long.toString(cat1.getId())))
                        , is(Integer.parseInt(Long.toString(cat2.getId()))))))
                .andExpect(jsonPath("$.catVoted", nullValue()));
    }

    @Test
    void voteForCat() throws Exception {
        Cat cat1 = testEntityManager.persist(new Cat("fake_url", 5));
        Cat cat2 = testEntityManager.persist(new Cat("fake_url", 3));
        User user = testEntityManager.persist(new User());
        CatMatch match = testEntityManager.persist(new CatMatch(user, cat1, cat2));
        testEntityManager.flush();
        Cookie cookie = new Cookie("userId", user.getId().toString());
        mockMvc.perform(post("/cats/match/vote")
                .cookie(cookie)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"catId1\":" + match.getCat1().getId() + "," +
                        "\"catId2\":" + match.getCat2().getId() + ", " +
                        "\"catIdVoted\":" + match.getCat1().getId() + "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cat1.nbVotes", is(6)))
                .andExpect(jsonPath("$.cat2.nbVotes", is(3)));

        mockMvc.perform(post("/cats/match/vote")
                .cookie(cookie)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"catId1\":" + match.getCat1().getId() + "," +
                        "\"catId2\":" + match.getCat2().getId() + ", " +
                        "\"catIdVoted\":" + match.getCat2().getId() + "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cat1.nbVotes", is(5)))
                .andExpect(jsonPath("$.cat2.nbVotes", is(4)));

    }

}
