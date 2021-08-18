package com.crud.controller;

import com.crud.entity.User;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/crud/user")
    public String getUserPage(Model model){
        model.addAttribute("authorizedUser", (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "user";
    }
}
