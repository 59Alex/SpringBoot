package com.preproject.thirdmodule.controllers;



import com.preproject.thirdmodule.service.UserService;
import com.preproject.thirdmodule.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService service;

    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }
    public AdminController() {}

    @GetMapping
    public String getAdminPageView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth_entity", service.loadUserByUsername(authentication.getName()));
        model.addAttribute("page", "admin_page");
        return "admin";
    }
}
