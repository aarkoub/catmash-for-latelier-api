package aarkoub.catmash.domain.user.service;

import aarkoub.catmash.db.user.IUserRepository;
import aarkoub.catmash.db.user.UserNotFoundException;
import aarkoub.catmash.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public User retrieveUser(@Nullable UUID userId) {
        if (userId != null) {
            try {
                return userRepository.find(userId);
            } catch (UserNotFoundException e) {
                return userRepository.add();
            }
        }
        return userRepository.add();
    }
}
