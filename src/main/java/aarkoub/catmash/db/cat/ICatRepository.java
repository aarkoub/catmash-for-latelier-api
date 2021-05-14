package aarkoub.catmash.db.cat;

import aarkoub.catmash.domain.cat.Cat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatRepository {

    long add(Cat cat);
    Cat increaseVote(long id) throws CatNotFoundException;
    Cat decreaseVote(long id) throws CatNotFoundException;
    List<Cat> getAll();
    Cat find(long id) throws CatNotFoundException;
}
