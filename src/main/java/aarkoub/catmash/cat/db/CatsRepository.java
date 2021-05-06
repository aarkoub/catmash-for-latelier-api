package aarkoub.catmash.cat.db;

import aarkoub.catmash.cat.domain.Cat;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CatsRepository extends CrudRepository<Cat, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE CAT SET nb_votes=?2 WHERE id=?1", nativeQuery = true)
    public int updateCatVotes(Long id, int votes);

}
