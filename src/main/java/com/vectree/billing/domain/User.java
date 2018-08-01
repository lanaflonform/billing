package com.vectree.billing.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User of the system.
 * @author Egor Voronyansky
 * @version 0.1.
 */
@Data @NoArgsConstructor
public class User {

    /**
     * Unique per user number.
     */
    private int id;

    /**
     * Email of the user.
     */
    private String email;

    /**
     * Password of the user, must be crypt before adding to the database.
     */
    private String password;

    /**
     * Wallet of the user.
     */
    private Account account;

}
