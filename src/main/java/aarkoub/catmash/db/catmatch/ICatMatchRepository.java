package aarkoub.catmash.db.catmatch;

import aarkoub.catmash.domain.catmatch.CatMatch;

import java.util.UUID;

public interface ICatMatchRepository {

    CatMatch add(UUID userId, long catId1, long catId2);
    CatMatch find(UUID userId, long catId1, long catId2) throws CatMatchNotFoundException;
    CatMatch changeVote(UUID userId, long catId1, long catId2, long catIdVoted) throws CatMatchNotFoundException;
}
