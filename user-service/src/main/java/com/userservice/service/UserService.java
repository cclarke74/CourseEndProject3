package com.userservice.service;

import com.userservice.dto.UserEntityRequest;
import com.userservice.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> listUsers();

    UserEntity createUser(UserEntityRequest user);

    void updateUser(Long userId, UserEntityRequest userEntityRequest);

    void deleteUser(Long id);

    UserEntity findUserById(Long id);

    UserEntity findUserByUserName(String username);
}
