package com.vectree.service;

import com.vectree.domain.User;
import com.vectree.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class work with user repository. It creates for next purposes:
 * 1. Controller does not call repository method, it must did service class
 * 2. Hide implementation of repository.
 * This class provide functional for CRUD operations with users.
 *
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repo) {
        userRepository = repo;
    }

    /**
     * Return a use with given id or thrown exception if user not exists at the database.
     * @param id unique per each user number.
     * @return User instance or thrown expcetion.
     */
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with given id not exists!"));
    }

    /**
     * Register a new user. If user a new at the system add, otherwise register
     * @param user instance of user for adding.
     */
    public void registerOrUpdate(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

}
