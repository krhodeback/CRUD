package com.crud.controller;

import com.crud.entity.Role;
import com.crud.entity.User;
import com.crud.service.RoleService;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller()
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("user", (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "admin";
    }

    @GetMapping("/admin/users")
    public String getUsersPage(Model model) {
        List<User> userList = userService.findAllUsers();
        model.addAttribute("userList", userList);
        model.addAttribute("newUser", new User());
        model.addAttribute("updateUser", new User());
        return "users";
    }

    @GetMapping("/admin/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/admin/registration")
    public String create(@ModelAttribute("user") User user, @RequestParam("role") String role) {
        user.setRoles(Set.of(roleService.findByName(role)));
        userService.saveNewUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserPage(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "adminUser";
    }

    @GetMapping("/admin/user/{id}/update")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        User updateUser = new User();
        updateUser.setId(id);
        model.addAttribute("updateUser", updateUser);
        return "updateUser";
    }

    @PostMapping("/admin/update")
    public String update(
            @RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("role") String roleName, @RequestParam("email") String email, @RequestParam("login") String login) {
        Role role = roleService.findByName(roleName);
        User user = userService.findUserById(id);
        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setRoles(Set.of(role));
        userService.updateUser(user);
        return "redirect:/admin/user/" + user.getId();
    }


}
