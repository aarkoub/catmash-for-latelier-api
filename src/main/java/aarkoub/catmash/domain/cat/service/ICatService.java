package aarkoub.catmash.domain.cat.service;

import aarkoub.catmash.domain.cat.Cat;

import java.util.List;

public interface ICatService {

    long addCat(Cat cat);
    Cat voteForCat(long id) throws Exception;
    List<Cat> getAllCats();

}
