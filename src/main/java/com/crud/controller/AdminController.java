package com.crud.controller;

import com.crud.entity.User;
import com.crud.service.RoleService;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/crud/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("authorizedUser", (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "admin";
    }

}
