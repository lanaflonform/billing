package com.vectree.controller;

import com.vectree.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void whenTryRegisterOrUpdateUserShouldCheckThatUserWasRegister() {
        User user = new User();
        user.setEmail("1@mail.com");
        user.setPassword("2pass");
        User registered = userController.registerOrUpdate(user);
        assertThat(registered.getPassword(), is("2pass"));

    }

    @Test
    public void whenTryGetUserByIdShouldCheckThatReturnCorrectUser() {
        User user = new User();
        user.setEmail("2pac@mail.ru");
        user.setPassword("password");
        user.setRoles(new HashSet<>());
        userController.registerOrUpdate(user);
        assertThat(userController.getUserById(user.getId()), is(user));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTryGetNotExistUserShouldCheckThatThrowException() {
        userController.getUserById(-1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTryRemoveUserShouldCheckThatUserWasRemoved() {
        User user = new User();
        user.setEmail("email");
        user.setPassword("password");
        userController.registerOrUpdate(user);
        userController.removeUser(user);
        userController.getUserById(user.getId());
    }
}