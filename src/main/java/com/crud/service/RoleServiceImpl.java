package com.crud.service;

import com.crud.dao.RoleRepository;
import com.crud.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Component
@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleDAO;

    @Autowired
    public RoleServiceImpl(RoleRepository roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void insert(Role role) {
        roleDAO.save(role);
    }

    @Override
    public Role findById(Long id) {
        return roleDAO.findById(id).get();
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
        return new HashSet<>(roleDAO.findAll());
    }
}
