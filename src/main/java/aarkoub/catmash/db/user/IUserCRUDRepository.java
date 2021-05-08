package aarkoub.catmash.db.user;

import aarkoub.catmash.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserCRUDRepository extends CrudRepository<User, UUID> {
}
