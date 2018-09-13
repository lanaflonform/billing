package com.vectree.billing.service.dao;

import com.vectree.billing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO interface for User.
 *
 * @version 0.1.
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
