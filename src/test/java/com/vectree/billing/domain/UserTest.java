package com.vectree.billing.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Role unit test check that all works fine.
 *
 * @version 0.1
 */
public class UserTest {
    private User user = new User();

    /**
     * Test get name from User.
     */
    @Test
    public void whenTryGetUsernameUserShouldCheckThatUserReturnNotNullValue() {
        assertThat(user.getUsername(), is(""));
    }

    /**
     * Test get email from User.
     */
    @Test
    public void whenTryGetEmailUserShouldCheckThatUserReturnNotNullValue() {
        assertThat(user.getEmail(), is(""));
    }

    /**
     * Test get password from User.
     */
    @Test
    public void whenTryGetPasswordUserShouldCheckThatUserReturnNotNullValue() {
        assertThat(user.getPassword(), is(""));
    }

    /**
     * Test toString() is not null
     */
    @Test
    public void whenTryGetToStringUserShouldCheckThatUserReturnStringValue() {
        assertThat(user.toString(), instanceOf(String.class));
    }

    /**
     * Test toString format
     */
    @Test
    public void whenTryGetToStringRoleShouldCheckThatRoleReturnTrueValue() {
        assertThat(user.toString(), is("User {id=0 username=\"\" email=\"\" password=\"\" " +
                "confirmPassword=\"null\" Account {id=0} roles {}}"));
    }
}
