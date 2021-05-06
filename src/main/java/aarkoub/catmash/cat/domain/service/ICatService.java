package aarkoub.catmash.cat.domain.service;

import aarkoub.catmash.cat.domain.Cat;

import java.util.List;

public interface ICatService {

    long addCat(Cat cat);
    Cat voteForCat(long id) throws Exception;
    List<Cat> getAllCats();

}
