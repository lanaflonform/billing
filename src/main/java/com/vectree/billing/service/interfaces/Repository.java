package com.vectree.billing.service.interfaces;

import java.util.List;

/**
 * Interface for User DAO.
 *
 * @param <T> Role or User
 * @version 0.1.
 */
public interface Repository<T> {

    void add(T t);

    void edit(T t);

    void delete(int id);

    T getById(int id);

    T getByName(String name);

    List<T> list();
}
