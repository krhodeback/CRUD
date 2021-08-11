package com.crud.dao;

import com.crud.entity.Role;
import com.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Component
@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    public RoleDAOImpl(@Qualifier("getEntityManager") EntityManager manager) {
        this.manager = manager;
    }


    @Override
    @Transactional
    public void insert(Role role) {
        manager.persist(role);
    }

    @Override
    @Transactional
    public Role findById(Long id) {
        return manager.find(Role.class, id);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        manager.remove(role);
    }

    @Override
    @Transactional
    public Role findByName(String name) {
        try {
            return manager
                    .createQuery("select r from Role r where r.name = :name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Set<Role> findAll() {
        return Set.copyOf(manager.createQuery("from Role", Role.class).getResultList());
    }


}
