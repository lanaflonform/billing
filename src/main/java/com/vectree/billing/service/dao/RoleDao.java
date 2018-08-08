package com.vectree.billing.service.dao;

import com.vectree.billing.domain.Role;
import com.vectree.billing.service.interfaces.RoleRepository;
import lombok.Cleanup;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO class for Role.
 *
 * @version 0.1.
 */
@Repository("roleDao")
public class RoleDao implements RoleRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Role role) {
        @Cleanup Session session = sessionFactory.openSession();
        session.persist(role);
        session.flush();
        LOGGER.info("Role successfully saved. Role: " + role);
    }

    @Override
    public void edit(Role role) {
        @Cleanup Session session = sessionFactory.openSession();
        session.update(role);
        session.flush();
        LOGGER.info("Role successfully updated. Role: " + role);
    }

    @Override
    public void delete(int id) {
        @Cleanup Session session = sessionFactory.openSession();
        Role role = (Role) session.get(Role.class, id);
        session.delete(role);
        session.flush();
        LOGGER.info("Role successfully deleted. Role: " + role);
    }

    @Override
    public Role getById(int id) {
        @Cleanup Session session = this.sessionFactory.openSession();
        return (Role) session.get(Role.class, id);
    }

    @Override
    public Role getByName(String name) {
        @Cleanup Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Role where name=:name");
        query.setParameter("name", name);
        return (Role) query.uniqueResult();
    }

    @Override
    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Role> list() {
        @Cleanup Session session = this.sessionFactory.openSession();
        return session.createQuery("from Role order by id").list();
    }
}
