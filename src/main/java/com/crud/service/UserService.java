package com.crud.service;

import com.crud.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveNewUser(User user);

    void deleteById(Long id);

    List<User> findAllUsers();

    void updateUser(User user);

    User findUserById(Long id);


}
