package aarkoub.catmash.cat.db;

import aarkoub.catmash.cat.domain.Cat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatRepository {

    long addCat(Cat cat);
    Cat voteForCat(long id) throws Exception;
    List<Cat> getAllCats();

}
