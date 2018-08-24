package com.vectree.billing.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class for Transaction of User.
 *
 * @version 0.1.
 */
@Data
@NoArgsConstructor
public class UserTransaction {

    /**
     * the User who sends money.
     */
    private User sender;
    /**
     * the User who recives money.
     */
    private User reciver;

    public UserTransaction(User sender, User reciver) {
        this.sender = sender;
        this.reciver = reciver;
    }
}
