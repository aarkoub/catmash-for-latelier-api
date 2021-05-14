package aarkoub.catmash.domain.catmatch.service;

import aarkoub.catmash.db.cat.CatNotFoundException;
import aarkoub.catmash.db.cat.ICatRepository;
import aarkoub.catmash.db.catmatch.CatMatchNotFoundException;
import aarkoub.catmash.db.catmatch.ICatMatchRepository;
import aarkoub.catmash.db.user.IUserRepository;
import aarkoub.catmash.db.user.UserNotFoundException;
import aarkoub.catmash.domain.cat.Cat;
import aarkoub.catmash.domain.catmatch.CatMatch;
import aarkoub.catmash.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class CatMatchServiceTest {

    @MockBean
    ICatMatchRepository catMatchMockRepository;

    @MockBean
    IUserRepository userMockRepository;
    
    @MockBean
    ICatRepository catMockRepository;

    @Autowired
    ICatMatchService catMatchService ;

    @Test
    public void testRetrieve() throws CatMatchNotFoundException, UserNotFoundException, CatNotFoundException {
        User user = new User(UUID.randomUUID());
        Cat cat1 = new Cat(1, "fakeurl", 0);
        Cat cat2 = new Cat(2, "fakeurl", 0);
        CatMatch cm = new CatMatch(user, cat1, cat2);
        Mockito.when(userMockRepository.find(user.getId())).thenReturn(user);
        Mockito.when(catMockRepository.find(cat1.getId())).thenReturn(cat1);
        Mockito.when(catMockRepository.find(cat2.getId())).thenReturn(cat2);
        Mockito.when(catMatchMockRepository.find(user.getId(), cat1.getId(), cat2.getId())).thenThrow(new CatMatchNotFoundException("mock"));
        Mockito.when(catMatchMockRepository.add(user, cat1, cat2)).thenReturn(cm);
        Assertions.assertEquals(cm, catMatchService.retrieve(user.getId(), cat1.getId(), cat2.getId()));
    }

    @Test
    public void testChangeVote() throws Exception {
        User user = new User(UUID.randomUUID());
        Cat cat1 = new Cat(1, "fakeurl", 1);
        Cat cat2 = new Cat(2, "fakeurl", 0);
        CatMatch cm = new CatMatch(user, cat1, cat2, cat1);
        Mockito.when(catMatchMockRepository.find(user.getId(), cat1.getId(), cat2.getId())).thenReturn(cm);
        Mockito.when(catMatchMockRepository.changeVote(user.getId(), cat1.getId(), cat2.getId(), cat2.getId())).thenReturn(cm);
        Mockito.when(catMockRepository.increaseVote(cat2.getId())).thenAnswer(invocation -> {
            cat2.setNbVotes(1);
            return cat2;
        });
        Mockito.when(catMockRepository.decreaseVote(cat1.getId())).thenAnswer(invocation -> {
            cat1.setNbVotes(0);
            return cat1;
        });
        catMatchService.changeVote(user.getId(), cat1.getId(), cat2.getId(), cat2.getId());
        Assertions.assertEquals(0, cat1.getNbVotes());
        Assertions.assertEquals(1, cat2.getNbVotes());
        
    }

    @Test
    public void testGenerateMatch() throws CatMatchNotFoundException, UserNotFoundException {

        Cat cat1 = new Cat(1L, "fakeurl", 4);
        Cat cat2 = new Cat(2L, "fakeurl", 0);
        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        User user = new User(UUID.randomUUID());
        Mockito.when(catMockRepository.getAll()).thenReturn(cats);

        Mockito.when(userMockRepository.find(user.getId())).thenReturn(user);

        Mockito.when(catMatchMockRepository.find(user.getId(), cat1.getId(), cat2.getId())).thenThrow(CatMatchNotFoundException.class);
        Mockito.when(catMatchMockRepository.add(user, cat1, cat2)).thenReturn(new CatMatch(user, cat1, cat2, null));

        Mockito.when(catMatchMockRepository.find(user.getId(), cat2.getId(), cat1.getId())).thenThrow(CatMatchNotFoundException.class);
        Mockito.when(catMatchMockRepository.add(user, cat2, cat1)).thenReturn(new CatMatch(user, cat1, cat2, null));

        CatMatch resMatch = catMatchService.generateMatch(user.getId());
        Assertions.assertEquals(new CatMatch(user, cat1, cat2, null), resMatch);
    }

}
