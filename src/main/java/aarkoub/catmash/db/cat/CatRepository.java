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
    public Cat increaseVote(long id) throws CatNotFoundException {
        Optional<Cat> cat = repository.findById(id);
        if(cat.isEmpty()){
            throw new CatNotFoundException("Database: Could find cat of id="+id);
        }
        repository.updateCatVotes(id, cat.get().getNbVotes()+1);
        return repository.findById(id).get();
    }

    @Override
    public Cat decreaseVote(long id) throws CatNotFoundException {
        Optional<Cat> cat = repository.findById(id);
        if(cat.isEmpty()){
            throw new CatNotFoundException("Database: Could find cat of id="+id);
        }
        assert(cat.get().getNbVotes()>0);
        repository.updateCatVotes(id, cat.get().getNbVotes()-1);
        return repository.findById(id).get();
    }

    @Override
    public List<Cat> getAll() {
        Iterable<Cat> catsIterable = repository.findAll();
        List<Cat> cats = new ArrayList<>();
        catsIterable.forEach(cat -> cats.add(cat));
        return cats;
    }

    @Override
    public Cat find(long id) throws CatNotFoundException {
        Optional<Cat> c = repository.findById(id);
        if(c.isEmpty()){
            throw (new CatNotFoundException("Can't find cat of id="+id));
        }
        return c.get();
    }
}
