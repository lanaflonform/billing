package com.vectree.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoleTest {

    @Test
    public void whenSetNewNameOfTheRoleShouldCheckThatItWorks() {
        Role role = new Role();
        role.setName("admin");
        assertThat(role.getName(), is("admin"));
    }

    @Test
    public void whenSetNewIdShouldCheckThatIsWasSaved() {
        Role role = new Role();
        role.setId(1L);
        assertThat(role.getId(), is(1L));
    }
}