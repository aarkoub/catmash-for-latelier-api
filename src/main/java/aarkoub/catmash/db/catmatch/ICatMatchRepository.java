package aarkoub.catmash.db.catmatch;

import aarkoub.catmash.domain.cat.Cat;
import aarkoub.catmash.domain.catmatch.CatMatch;
import aarkoub.catmash.domain.user.User;

import java.util.UUID;

public interface ICatMatchRepository {

    CatMatch add(User user, Cat cat1, Cat cat2);
    CatMatch find(UUID userId, long catId1, long catId2) throws CatMatchNotFoundException;
    CatMatch changeVote(UUID userId, long catId1, long catId2, long catIdVoted) throws CatMatchNotFoundException;
}
