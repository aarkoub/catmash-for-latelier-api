package aarkoub.catmash.domain.user.service;

import aarkoub.catmash.db.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UUID addUser() {
        return userRepository.add();
    }
}
