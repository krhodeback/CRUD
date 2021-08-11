package com.crud.dao;

import com.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Repository

public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager manager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDAOImpl(@Qualifier("getEntityManager") EntityManager manager) {
        this.manager = manager;
        passwordEncoder = new BCryptPasswordEncoder();
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
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return manager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        manager.merge(user);

    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails findUserByLogin(String login) {
        try {
            return manager
                    .createQuery("select u from User u where u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {

            System.err.println("Cant get userDetails : " + e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return manager.find(User.class, id);
    }
}
