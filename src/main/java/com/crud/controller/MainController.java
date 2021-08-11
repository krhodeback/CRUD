package com.crud.controller;

import com.crud.service.RoleService;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private final UserService userService;

    private final RoleService roleDAO;

    @Autowired
    public MainController(UserService userService, RoleService roleDAO) {
        this.userService = userService;
        this.roleDAO = roleDAO;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }




}
