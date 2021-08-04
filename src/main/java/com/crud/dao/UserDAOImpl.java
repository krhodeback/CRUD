package com.crud.dao;

import com.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Repository

public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    public UserDAOImpl(@Qualifier("getEntityManager") EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void saveNewUser(User user) {
        manager.persist(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        manager.createQuery("delete from User u where u.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        List<User> userList = manager.createQuery("from User", User.class).getResultList();
        return userList;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        manager.merge(user);

    }

    @Override
    @Transactional
    public UserDetails findUserByLogin(String login) {
        UserDetails user = null;
        try {
            user = manager
                    .createQuery("select u from User u where u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            System.err.println("UserDetails :" + user);
        } catch (Exception e) {
            System.err.println("Cant get userDetails : " +e.getMessage());
        }
        return user;
    }
    @Override
    @Transactional
    public User findUserById(Long id) {
        return manager.find(User.class, id);
    }
}
