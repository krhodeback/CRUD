package com.crud.dao;

import com.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManagerFactory factory;

    @Autowired
    public UserDAOImpl(@Qualifier(value = "entityManagerFactory") EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void insert(User user) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
            System.err.println("User " + user.getLogin() + " have been added");
        } catch (Exception e) {
            System.err.println("Cant insert " + user.getLogin() + " exception : " + e.getMessage());

        }
    }

    @Override
    public void deleteById(Long id) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            User user = manager.find(User.class, id);
            manager.remove(user);
            manager.getTransaction().commit();
            System.err.println("User " + user.getLogin() + " have been deleted");
        } catch (Exception e) {
            System.err.println("Cant delete  exception :" + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager manager = factory.createEntityManager();
        List<User> userList = new ArrayList<>();
        try {
            userList = manager.createQuery("select u from User u", User.class).getResultList();
            manager.flush();
            manager.close();
        } catch (Exception e) {
            System.out.println("Cant get all users : " + e.getMessage());
        }
        return userList;
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            System.out.println("User " + user.getLogin() + " have been updated");
        } catch (Exception e) {
            System.out.println("Cant update user exception :" + e.getMessage());

        }

    }
}
