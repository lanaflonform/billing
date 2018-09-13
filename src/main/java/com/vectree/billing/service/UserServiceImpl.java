package com.vectree.billing.service;

import com.vectree.billing.domain.Account;
import com.vectree.billing.domain.Role;
import com.vectree.billing.domain.User;
import com.vectree.billing.service.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        Account account = new Account(0L, new BigDecimal("0.0"), new BigDecimal("0.0"));
        account.setUser(user);
        user.setAccount(account);

        userDao.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userDao.getOne(id);
        userDao.delete(user);
    }

    @Override
    public User getOne(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll(new Sort(Sort.Direction.ASC, "username"));
    }
}
