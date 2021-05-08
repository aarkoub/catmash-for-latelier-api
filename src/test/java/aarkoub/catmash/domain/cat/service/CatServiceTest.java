package aarkoub.catmash.domain.cat.service;

import aarkoub.catmash.db.cat.ICatRepository;
import aarkoub.catmash.domain.cat.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CatServiceTest {

    @MockBean
    ICatRepository mockRepository;

    @Autowired
    ICatService catService ;

    @Test
    public void testAddCat(){

        Cat cat = new Cat(2L, "fake_url", 5);
        Mockito.when(mockRepository.addCat(cat)).thenReturn(1L);
        Assertions.assertEquals(1, catService.addCat(cat));
    }

    @Test
    public void testGetAllCats(){
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat(1, "fake_url", 5));
        cats.add(new Cat(2, "fake_url", 5));
        Mockito.when(mockRepository.getAllCats()).thenReturn(cats);
        Assertions.assertEquals(cats, catService.getAllCats());
    }

    @Test
    public void testVoteForCat() throws Exception {
        Cat cat = new Cat(1, "fake_url", 5);
        Mockito.when(mockRepository.voteForCat(2)).thenReturn(cat);
        Assertions.assertEquals(5, catService.voteForCat(2).getNbVotes());
    }

}
