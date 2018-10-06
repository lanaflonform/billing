package com.vectree.domain;

import lombok.val;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {


    @Test
    public void whenSetAllFieldsShouldCheckThatAllFieldsSaved() {
        val user = new User();
        user.setId(1L);
        user.setEmail("email@email.com");
        user.setPassword("password");
        val wallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("user"));
        user.setWallet(wallet);
        user.setRoles(roles);

        assertThat("ID is not set.", user.getId(), is(1L));
        assertThat("Email is not set.", user.getEmail(), is("email@email.com"));
        assertThat("Password is not set", user.getPassword(), is("password"));
        assertThat("Wallet is not set.", user.getWallet(), is(wallet));
        assertThat("Roles is not set.", user.getRoles(), is(roles));
    }

}