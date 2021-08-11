package com.crud.service;

import com.crud.dao.RoleDAO;
import com.crud.dao.RoleDAOImpl;
import com.crud.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Component
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void insert(Role role) {
        roleDAO.insert(role);
    }

    @Override
    public Role findById(Long id) {
        return roleDAO.findById(id);
    }

    @Override
    public void delete(Role role) {
        roleDAO.delete(role);
    }

    @Override
    public Role findByName(String name) {
        return roleDAO.findByName(name);
    }

    @Override
    public Set<Role> findAll() {
        return roleDAO.findAll();
    }
}
