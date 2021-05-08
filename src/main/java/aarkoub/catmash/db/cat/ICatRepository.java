package aarkoub.catmash.db.cat;

import aarkoub.catmash.domain.cat.Cat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatRepository {

    long addCat(Cat cat);
    Cat voteForCat(long id) throws Exception;
    List<Cat> getAllCats();

}
