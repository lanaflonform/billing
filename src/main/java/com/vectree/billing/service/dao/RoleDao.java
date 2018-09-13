package com.vectree.billing.service.dao;

import com.vectree.billing.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO interface for Role.
 *
 * @version 0.1.
 */
public interface RoleDao extends JpaRepository<Role, Long> {
}
