package aarkoub.catmash.db.user;

import aarkoub.catmash.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepository implements IUserRepository {

    @Autowired
    IUserCRUDRepository repository;

    @Override
    public User add() {
        User user = new User();
        return repository.save(user);
    }

    @Override
    public User find(UUID id) throws UserNotFoundException {
        Optional<User> u = repository.findById(id);
        if(u.isEmpty()){
            throw (new UserNotFoundException("Can't find user of id="+id));
        }
        return u.get();
    }

}
