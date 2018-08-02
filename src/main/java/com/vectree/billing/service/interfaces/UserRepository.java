package com.vectree.billing.service.interfaces;

import com.vectree.billing.domain.User;

import java.util.List;

/**
 * Interface for User DAO.
 *
 * @version 0.1.
 */
public interface UserRepository {

    void add(User user);

    void edit(User user);

    User getUserById(int id);

    List<User> list();
}
