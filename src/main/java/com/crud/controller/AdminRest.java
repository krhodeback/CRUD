package com.crud.controller;

import com.crud.entity.Role;
import com.crud.entity.User;
import com.crud.service.RoleService;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/crud")
public class AdminRest {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admins")
    public ResponseEntity<List<User>> getAdminPages(Model model) {
        return new ResponseEntity<List<User>>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/admins/{id}")
    public User getAdminPages(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }


    @PostMapping("/admin/registration")
    public void create(@RequestBody User user) {
        userService.saveNewUser(user);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }


    @PutMapping("/admin/updateUser")
    public void update(
            @RequestBody User user) {
        System.out.println(user);
        userService.updateUser(user);
    }

    @GetMapping("/admin/findRole/{role}")
    public Role findRole(@PathVariable("role") String roleName) {
        return roleService.findByName(roleName);
    }


}
