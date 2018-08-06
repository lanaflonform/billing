package com.vectree.billing.service.dao;

import com.vectree.billing.domain.User;
import com.vectree.billing.service.interfaces.UserRepository;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DAO class for User.
 *
 * @version 0.1.
 */
public class UserDao implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(value = "transactionManagerHibernate")
    public void add(User user) {
        @Cleanup Session session = sessionFactory.openSession();
        session.persist(user);
        session.flush();
        logger.info("User successfully saved. User id: " + user.getId());
    }

    @Override
    @Transactional(value = "transactionManagerHibernate")
    public void edit(User user) {
        @Cleanup Session session = sessionFactory.openSession();
        session.update(user);
        session.flush();
        logger.info("User successfully updated. User id: " + user.getId());
    }

    @Override
    @Transactional(value = "transactionManagerHibernate")
    public User getUserById(int id) {
        @Cleanup Session session = this.sessionFactory.openSession();
        return (User) session.get(User.class, id);
    }

    @Override
    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    @Transactional(value = "transactionManagerHibernate")
    public List<User> list() {
        @Cleanup Session session = this.sessionFactory.openSession();
        return session.createQuery("from User order by id").list();
    }
}
