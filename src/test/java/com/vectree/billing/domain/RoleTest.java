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
public class RoleTest {
    private Role role = new Role();

    /**
     * Test get name from Role.
     */
    @Test
    public void whenTryGetNameRoleShouldCheckThatRoleReturnNotNullValue() {
        assertThat(role.getName(), is(""));
    }

    /**
     * Test toString() is not null
     */
    @Test
    public void whenTryGetToStringRoleShouldCheckThatRoleReturnStringValue() {
        assertThat(role.toString(), instanceOf(String.class));
    }

    /**
     * Test toString format
     */
    @Test
    public void whenTryGetToStringRoleShouldCheckThatRoleReturnTrueValue() {
        assertThat(role.toString(), is("Role {id=null name=\"\"}"));
    }


}
