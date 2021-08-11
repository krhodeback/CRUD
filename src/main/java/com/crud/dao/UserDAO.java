package com.crud.dao;

import com.crud.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDAO {
    void saveNewUser(User user);

    void deleteById(Long id);

    List<User> findAllUsers();

    void updateUser(User user);

    UserDetails findUserByLogin(String login);

    User findUserById(Long id);


}
