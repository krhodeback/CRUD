package com.crud.service;

import com.crud.dao.UserDAO;
import com.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveNewUser(user);
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public void updateUser(User user) {
        User dbUser = userDAO.findUserById(user.getId());
        String password = user.getPassword();
        if (password.equals("") || passwordEncoder.matches(password, dbUser.getPassword())) {
            user.setPassword(dbUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(password));
        }
        userDAO.updateUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDAO.findUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.findUserByLogin(s);
    }
}
