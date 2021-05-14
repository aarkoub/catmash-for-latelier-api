package aarkoub.catmash.db.catmatch;

import aarkoub.catmash.domain.cat.Cat;
import aarkoub.catmash.domain.catmatch.CatMatch;
import aarkoub.catmash.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CatMatchRepository implements ICatMatchRepository {

    @Autowired
    ICatMatchCRUDRepository repository;

    @Override
    public CatMatch add(User user, Cat cat1, Cat cat2) {
        CatMatch catMatch = new CatMatch(user, cat1, cat2);
        return repository.save(catMatch);
    }

    @Override
    public CatMatch find(UUID userId, long catId1, long catId2) throws CatMatchNotFoundException {
        Optional<CatMatch> resultQuery = repository.findById(new CatMatch.CatMatchPK(userId, catId1, catId2));
        if(resultQuery.isEmpty())
            throw new CatMatchNotFoundException("Couldn't find cat match of id user_id=" + userId +
                    ", cat_id_1=" + catId1 +", cat_id_2="+ catId2);
        return resultQuery.get();
    }

    @Override
    public CatMatch changeVote(UUID userId, long catId1, long catId2, long catIdVoted) throws CatMatchNotFoundException {
        CatMatch.CatMatchPK primaryKey = new CatMatch.CatMatchPK(userId, catId1, catId2);
        Optional<CatMatch> resultQuery = repository.findById(primaryKey);
        if(resultQuery.isEmpty())
            throw new CatMatchNotFoundException("Couldn't find cat match of id user_id=" + userId +
                    ", cat_id_1=" + catId1 +", cat_id_2="+ catId2);
        CatMatch cm = resultQuery.get();
        if(cm.getCatVoted()!=null)
            assert(resultQuery.get().getCatVoted().getId()!=catIdVoted);
        repository.updateCatIdVoted(userId, catId1, catId2, catIdVoted);
        return repository.findById(primaryKey).get();
    }

}
