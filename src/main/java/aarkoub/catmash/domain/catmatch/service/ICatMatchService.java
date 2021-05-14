package aarkoub.catmash.domain.catmatch.service;


import aarkoub.catmash.db.cat.CatNotFoundException;
import aarkoub.catmash.db.catmatch.CatMatchNotFoundException;
import aarkoub.catmash.db.user.UserNotFoundException;
import aarkoub.catmash.domain.catmatch.CatMatch;


import java.util.UUID;

public interface ICatMatchService {

    CatMatch retrieve(UUID userId, long catId1, long catId2) throws UserNotFoundException, CatNotFoundException;
    CatMatch changeVote(UUID userId, long catId1, long catId2, long catIdVoted) throws CatMatchNotFoundException,
            CatNotFoundException;
    CatMatch generateMatch(UUID userId) throws UserNotFoundException;

}
