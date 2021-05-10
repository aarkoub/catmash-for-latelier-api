package aarkoub.catmash.db.user;

import aarkoub.catmash.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository {
    UUID add();
}
