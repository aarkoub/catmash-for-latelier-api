package aarkoub.catmash.db.catmatch;

import aarkoub.catmash.domain.catmatch.CatMatch;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICatMatchCRUDRepository extends CrudRepository<CatMatch, CatMatch.CatMatchPK> {

    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE CAT_MATCH SET cat_id_voted=?4 WHERE user_id=?1 and cat_id_1=?2 and cat_id_2=?3", nativeQuery = true)
    int updateCatIdVoted(UUID userId, long catId1, long catId2, long catIdVoted);
}
