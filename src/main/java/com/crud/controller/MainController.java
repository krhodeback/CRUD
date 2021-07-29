package com.crud.controller;

import com.crud.entity.User;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getIndex(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        model.addAttribute("newUser", new User());
        model.addAttribute("updateUser", new User());
        return "index";
    }

    @GetMapping("/main")
    public String getMain() {
        return "main";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("newUser") User user) {
        userService.insert(user);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Long id, @RequestParam("login") String login, @RequestParam("email") String email) {
        User user = new User(login, email);
        user.setId(id);
        userService.update(user);
        return "redirect:/";
    }


}
