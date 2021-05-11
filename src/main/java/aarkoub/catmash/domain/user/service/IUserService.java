package aarkoub.catmash.domain.user.service;

import aarkoub.catmash.domain.user.User;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface IUserService {
    User retrieveUser(@Nullable UUID userId);
}
