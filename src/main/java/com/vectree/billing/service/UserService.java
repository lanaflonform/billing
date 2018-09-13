package com.vectree.billing.service;

import com.vectree.billing.domain.User;

import java.util.List;

/**
 * Service interface for work with user instance.
 *
 * @version 0.1
 */
public interface UserService {

    void save(User user);

    void delete(Long id);

    User getOne(Long id);

    User findByUsername(String username);

    List<User> getAll();
}
