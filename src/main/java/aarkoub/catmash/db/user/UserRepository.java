package aarkoub.catmash.db.user;

import aarkoub.catmash.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRepository implements IUserRepository {

    @Autowired
    IUserCRUDRepository repository;

    @Override
    public UUID addUser() {
        User user = new User();
        return repository.save(user).getId();
    }
}
