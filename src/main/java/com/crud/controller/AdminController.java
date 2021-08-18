package com.crud.controller;

import com.crud.entity.Role;
import com.crud.entity.User;
import com.crud.service.RoleService;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/crud")
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
        model.addAttribute("authorizedUser", (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("userList", userService.findAllUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }


    @PostMapping("/admin/registration")
    public String create(@ModelAttribute("user") User user, @RequestParam("role") String... roleList) {
        Set<Role> roleSet = new HashSet<>();
        for (String role :
                roleList) {
            roleSet.add(roleService.findByName(role));
        }
        user.setRoles(roleSet);
        userService.saveNewUser(user);
        return "redirect:/crud/admin";
    }

    @DeleteMapping("/admin/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/crud/admin";
    }


    @PutMapping("/admin/updateUser")
    public String update(
            @RequestParam("userid") Long id,
            @RequestParam("username") String name,
            @RequestParam("useremail") String email,
            @RequestParam("userlogin") String login,
            @RequestParam("userpassword") String password,
            @RequestParam("userrole") String... roleList) {
        Set<Role> roleSet = new HashSet<>();
        for (String role :
                roleList) {
            roleSet.add(roleService.findByName(role));
        }
        User user = userService.findUserById(id);
        user.setPassword(password);
        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/crud/admin";
    }


}
