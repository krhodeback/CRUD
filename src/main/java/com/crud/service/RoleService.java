package com.crud.service;

import com.crud.entity.Role;

public interface RoleService {
    void insert(Role role);

    Role findById(Long id);

    void delete(Role role);

    Role findByName(String name);

}
