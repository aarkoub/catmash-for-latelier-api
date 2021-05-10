package aarkoub.catmash.db.cat;

import aarkoub.catmash.domain.cat.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CatRepository implements ICatRepository {

    @Autowired
    ICatCRUDRepository repository;

    @Override
    public long add(Cat cat) {
        return repository.save(cat).getId();
    }

    @Override
    public Cat vote(long id) throws Exception {
        Optional<Cat> cat = repository.findById(id);
        if(cat.isEmpty()){
            throw new Exception("Database: Could find cat of id="+id);
        }
        repository.updateCatVotes(id, cat.get().getNbVotes()+1);
        return repository.findById(id).get();
    }

    @Override
    public List<Cat> getAll() {
        Iterable<Cat> catsIterable = repository.findAll();
        List<Cat> cats = new ArrayList<>();
        catsIterable.forEach(cat -> cats.add(cat));
        return cats;
    }
}
