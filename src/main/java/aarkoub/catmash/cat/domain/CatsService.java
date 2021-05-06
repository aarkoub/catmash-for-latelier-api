package aarkoub.catmash.cat.domain;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatsService {

    long addCat(Cat cat);
    Cat voteForCat(long id) throws Exception;
    List<Cat> getAllCats();

}
