package aarkoub.catmash.cat.domain.service;

import aarkoub.catmash.cat.db.ICatRepository;
import aarkoub.catmash.cat.domain.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CatServiceTest {

    private class MockedCatRepository implements ICatRepository {

        @Override
        public long addCat(Cat cat) {
            return 1;
        }

        @Override
        public Cat voteForCat(long id) throws Exception {
            return new Cat(1, "fake_url", 5);
        }

        @Override
        public List<Cat> getAllCats() {
            Cat c = new Cat(1, "fake_url", 5);
            Cat c2 = new Cat(2, "fake_url", 5);
            List<Cat> cats = new ArrayList<>();
            cats.add(c);
            cats.add(c2);
            return cats;
        }
    }

    CatService catService = new CatService(new MockedCatRepository());

    @Test
    public void testAddCat(){
        Cat cat = new Cat(2L, "fake_url", 5);
        Assertions.assertEquals(1, catService.addCat(cat));
    }

    @Test
    public void testGetAllCats(){
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat(1, "fake_url", 5));
        cats.add(new Cat(2, "fake_url", 5));
        Assertions.assertEquals(cats, catService.getAllCats());
    }

    @Test
    public void testVoteForCat() throws Exception {
        Assertions.assertEquals(5, catService.voteForCat(2).getNbVotes());
    }

}
