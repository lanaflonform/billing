package com.vectree.controller;

import com.vectree.domain.User;
import com.vectree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        userService = service;
    }

    public User getUserById(Long id) {
        return userService.getById(id);
    }

    public User registerOrUpdate(User user) {
        userService.registerOrUpdate(user);
        return this.userService.getById(user.getId());
    }

    public void removeUser(User user) {
        userService.delete(user);
    }
}
