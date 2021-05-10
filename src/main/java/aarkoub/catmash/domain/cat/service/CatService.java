package aarkoub.catmash.domain.cat.service;

import aarkoub.catmash.db.cat.ICatRepository;
import aarkoub.catmash.domain.cat.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService implements ICatService {

    @Autowired
    ICatRepository catRepository;

    public CatService(ICatRepository catRepository){
        this.catRepository = catRepository;
    }

    @Override
    public long addCat(Cat cat) {
        return catRepository.add(cat);
    }

    @Override
    public Cat voteForCat(long id) throws Exception {
        return catRepository.vote(id);
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.getAll();
    }
}
