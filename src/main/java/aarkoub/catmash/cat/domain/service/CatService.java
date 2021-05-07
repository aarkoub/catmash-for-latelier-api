package aarkoub.catmash.cat.domain.service;

import aarkoub.catmash.cat.db.CatRepositorySQL;
import aarkoub.catmash.cat.db.ICatRepository;
import aarkoub.catmash.cat.domain.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService implements ICatService {

    @Autowired
    ICatRepository catRepository;

    public CatService(){
        this.catRepository = new CatRepositorySQL();
    }

    public CatService(ICatRepository catRepository){
        this.catRepository = catRepository;
    }

    @Override
    public long addCat(Cat cat) {
        return catRepository.addCat(cat);
    }

    @Override
    public Cat voteForCat(long id) throws Exception {
        return catRepository.voteForCat(id);
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.getAllCats();
    }
}
