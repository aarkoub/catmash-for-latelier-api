package aarkoub.catmash.domain.cat.service;

import aarkoub.catmash.domain.cat.Cat;

import java.util.List;

public interface ICatService {

    long add(Cat cat);
    Cat increaseVote(long id) throws Exception;
    Cat decreaseVote(long id) throws Exception;
    List<Cat> getAll();

}
