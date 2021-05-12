package aarkoub.catmash.domain.catmatch.service;


import aarkoub.catmash.domain.catmatch.CatMatch;


import java.util.UUID;

public interface ICatMatchService {

    CatMatch retrieve(UUID userId, long catId1, long catId2);
    CatMatch changeVote(UUID userId, long catId1, long catId2, long catIdVoted) throws Exception;
    CatMatch generateMatch(UUID userId);

}
