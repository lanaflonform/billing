package com.vectree.service;

import com.vectree.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test(expected = IllegalArgumentException.class)
    public void whenTryNotExistUserShouldCheckThatServiceThrowException() {
        userService.getById(0L);
    }

    @Test
    public void whenTryAddUserShouldCheckThatUserWasAdded() {
        User user = new User();
        user.setPassword("password");
        user.setEmail("1@mail.ru");
        userService.registerOrUpdate(user);
        assertThat(userService.getById(user.getId()).getEmail(), is("1@mail.ru"));
    }

    @Test
    public void whenTryEditUserShouldCheckThatUserWasUpdated() {
        User user = new User();
        user.setEmail("1@gmail.com");
        user.setPassword("password");
        userService.registerOrUpdate(user);
        User fromDb = userService.getById(user.getId());
        fromDb.setEmail("2@gmail.com");
        userService.registerOrUpdate(fromDb);
        user = userService.getById(user.getId());
        assertThat(user.getEmail(), is("2@gmail.com"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTryRemoveUserShouldCheckThatWasRemoved() {
        User user = new User();
        user.setEmail("1@mail.ru");
        user.setPassword("password");
        userService.registerOrUpdate(user);
        userService.delete(user);
        userService.getById(user.getId());
    }

}