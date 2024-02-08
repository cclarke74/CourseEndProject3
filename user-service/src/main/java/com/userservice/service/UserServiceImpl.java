package com.userservice.service;


import com.userservice.dto.UserEntityRequest;
import com.userservice.entity.UserEntity;
import com.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> listUsers() {
        logger.debug("Listing Users...");
        return userRepository.findAll();
    }

    @Override
    public UserEntity createUser(UserEntityRequest userEntityRequest) {
        logger.debug("Creating User... {}", userEntityRequest);

        UserEntity userEntity = UserEntity.builder()
                .firstName(userEntityRequest.getFirstName())
                .lastName(userEntityRequest.getLastName())
                .username(userEntityRequest.getUsername())
                .password(passwordEncoder.encode(userEntityRequest.getPassword()))
                .status(userEntityRequest.getStatus())
                .birth(userEntityRequest.getBirth())
                .userType(UserEntity.UserType.USER)
                .build();
        return userRepository.save(userEntity);
    }

    @Override
    public void updateUser(Long userId, UserEntityRequest userEntityRequest) {
        logger.debug("Updating User... {}", userEntityRequest);
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            if (userEntityRequest.getUsername() != null) {
                existingUser.setUsername(userEntityRequest.getUsername());
            }
            if (userEntityRequest.getFirstName() != null) {
                existingUser.setFirstName(userEntityRequest.getFirstName());
            }
            if (userEntityRequest.getLastName() != null) {
                existingUser.setLastName(userEntityRequest.getLastName());
            }
            if (userEntityRequest.getPassword() != null) {
                existingUser.setPassword(userEntityRequest.getPassword());
            }
            if (userEntityRequest.getBirth() != null) {
                existingUser.setBirth(userEntityRequest.getBirth());
            }
            if (userEntityRequest.getStatus() != null) {
                existingUser.setStatus(userEntityRequest.getStatus());
            }
            userRepository.save(existingUser);
        } else {
            logger.warn("User with ID {} not found. Update operation aborted.", userId);
        }
    }

    @Override
    public void deleteUser(Long id) {
        logger.debug("Deleting User... UserID: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findUserById(Long id) {
        logger.debug("Search User By id... UserID: {}", id);
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public UserEntity findUserByUserName(String username) {
        logger.debug("Search User By username... Username: {}", username);
        return userRepository.findByUsername(username);
    }
}
