package com.preproject.thirdmodule.controllers;


import com.preproject.thirdmodule.service.UserService;
import com.preproject.thirdmodule.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
    public UserController() {}

    @GetMapping
    public String getUserView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth_entity", service.loadUserByUsername(authentication.getName()));
        model.addAttribute("page", "user_page");
        return "user";
    }
}
