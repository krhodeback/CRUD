package com.crud.service;

import com.crud.entity.User;

import java.util.List;

public interface UserService {
    void insert(User user);

    void deleteById(Long id);

    List<User> findAll();

    void update(User user);
}
