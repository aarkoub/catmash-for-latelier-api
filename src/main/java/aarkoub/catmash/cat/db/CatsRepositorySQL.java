package aarkoub.catmash.cat.db;

import aarkoub.catmash.cat.domain.Cat;
import aarkoub.catmash.cat.domain.CatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CatsRepositorySQL implements CatsService {

    @Autowired
    CatsRepository repository;

    @Override
    public long addCat(Cat cat) {
        return repository.save(cat).getId();
    }

    @Override
    public Cat voteForCat(long id) throws Exception {
        Optional<Cat> cat = repository.findById(id);
        if(cat.isEmpty()){
            throw new Exception("Database: Could find cat of id="+id);
        }
        repository.updateCatVotes(id, cat.get().getNbVotes()+1);
        return repository.findById(id).get();
    }

    @Override
    public List<Cat> getAllCats() {
        Iterable<Cat> catsIterable = repository.findAll();
        List<Cat> cats = new ArrayList<>();
        catsIterable.forEach(cat -> cats.add(cat));
        return cats;
    }
}
