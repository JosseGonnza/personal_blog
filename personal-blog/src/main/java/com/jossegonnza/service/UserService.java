package com.jossegonnza.service;

import com.jossegonnza.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    void createUser(UserEntity user);
    Optional<UserEntity> getUserById(Long id);
    Optional<UserEntity> getUserByUsername(String username);
}
