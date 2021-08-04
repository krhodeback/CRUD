package com.crud.dao;

import com.crud.entity.Role;

public interface RoleDAO {
    void insert(Role role);

    Role findById(Long id);

    void delete(Role role);

    Role findByName(String name);

}
