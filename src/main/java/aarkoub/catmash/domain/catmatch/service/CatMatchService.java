package aarkoub.catmash.domain.catmatch.service;

import aarkoub.catmash.db.cat.ICatRepository;
import aarkoub.catmash.db.catmatch.CatMatchNotFoundException;
import aarkoub.catmash.db.catmatch.ICatMatchRepository;
import aarkoub.catmash.domain.cat.Cat;
import aarkoub.catmash.domain.catmatch.CatMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
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
    @Transactional(rollbackFor = Exception.class)
    public CatMatch changeVote(UUID userId, long catId1, long catId2, long catIdVoted) throws Exception {

        CatMatch match = catMatchRepository.find(userId, catId1, catId2);
        catMatchRepository.changeVote(userId, catId1, catId2, catIdVoted);
        catRepository.increaseVote(catIdVoted);
        if(match.getCatVoted()!=null){
            catRepository.decreaseVote(catIdVoted==catId1?catId2:catId1);
        }
        return catMatchRepository.find(userId, catId1, catId2);
    }

    @Override
    public CatMatch generateMatch(UUID userId) {

        List<Cat> cats = catRepository.getAll();
        int nbCats = cats.size();
        Cat cat1=null, cat2=null;
        Random rand = new Random();
        while(cat1==cat2) {
            cat1 = cats.get(Math.abs(rand.nextInt() % nbCats));
            cat2 = cats.get(Math.abs(rand.nextInt() % nbCats));
        }
        CatMatch match;
        try {
            match = catMatchRepository.find(userId, cat1.getId(), cat2.getId());
        } catch (CatMatchNotFoundException e) {
            match = catMatchRepository.add(userId, cat1.getId(), cat2.getId());
        }
        return match;
    }



}
