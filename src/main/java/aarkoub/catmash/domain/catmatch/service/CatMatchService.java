package aarkoub.catmash.domain.catmatch.service;

import aarkoub.catmash.db.cat.ICatRepository;
import aarkoub.catmash.db.catmatch.CatMatchNotFoundException;
import aarkoub.catmash.db.catmatch.ICatMatchRepository;
import aarkoub.catmash.domain.catmatch.CatMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CatMatchService implements ICatMatchService {

    @Autowired
    private ICatMatchRepository catMatchRepository;

    @Autowired
    private ICatRepository catRepository;

    @Override
    public CatMatch retrieve(UUID userId, long catId1, long catId2) {
        try {
            return catMatchRepository.find(userId, catId1, catId2);
        } catch (CatMatchNotFoundException e) {
            return catMatchRepository.add(userId, catId1, catId2);
        }

    }

    @Override
    public CatMatch changeVote(UUID userId, long catId1, long catId2, long catIdVoted) throws Exception {
        catMatchRepository.changeVote(userId, catId1, catId2, catIdVoted);
        catRepository.increaseVote(catIdVoted);
        catRepository.decreaseVote(catIdVoted==catId1?catId2:catId1);
        return catMatchRepository.changeVote(userId, catId1, catId2, catIdVoted);
    }
}
