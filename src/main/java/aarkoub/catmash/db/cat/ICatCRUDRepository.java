package aarkoub.catmash.db.cat;

import aarkoub.catmash.domain.cat.Cat;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICatCRUDRepository extends CrudRepository<Cat, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE CAT SET nb_votes=?2 WHERE id=?1", nativeQuery = true)
    public int updateCatVotes(Long id, int votes);

}
