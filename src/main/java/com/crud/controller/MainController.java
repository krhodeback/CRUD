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


    //    @PostMapping("/update")
//    public String update(@RequestParam("id") Long id, @RequestParam("login") String login, @RequestParam("email") String email) {
//        User user
//        user.setId(id);
//        userService.update(user);
//        return "redirect:/";
//    }
    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") Long id, Model model) {

        return "/WEB-INF/pages/adminUser.html";
    }


}
