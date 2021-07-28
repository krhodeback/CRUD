package com.crud.dao;

import com.crud.entity.User;

import java.util.List;

public interface UserDAO {
    void insert(User user);

    void deleteById(Long id);

    List<User> findAll();

    void update(User user);
}
