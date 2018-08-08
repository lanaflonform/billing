package com.vectree.billing.service;

import com.vectree.billing.domain.Account;
import com.vectree.billing.domain.Role;
import com.vectree.billing.domain.User;
import com.vectree.billing.service.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.vectree.billing.utils.Constants.ROLE_USER;
import static com.vectree.billing.utils.Constants.ROLE_USER_NAME;

/**
 * Service for work with user instance.
 *
 * @version 0.1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(value = "transactionManager")
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<User> users = new HashSet<>();
        users.add(user);

        Role role = new Role();
        role.setId(ROLE_USER);
        role.setName(ROLE_USER_NAME);
        role.setUsers(users);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        Account account = new Account(0, new BigDecimal("0.0"), new BigDecimal("0.0"));
        account.setUser(user);
        user.setAccount(account);
        user.setEmail("");

        userDao.add(user);
    }

    @Override
    @Transactional(value = "transactionManager")
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    @Transactional(value = "transactionManager")
    public User getOne(int id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional(value = "transactionManager")
    public User findByUsername(String username) {
        return userDao.getByName(username);
    }

    @Override
    @Transactional(value = "transactionManager")
    public List<User> getAll() {
        List<User> users = userDao.list();
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getUsername().compareToIgnoreCase(o2.getUsername());
            }
        });
        return users;
    }
}
