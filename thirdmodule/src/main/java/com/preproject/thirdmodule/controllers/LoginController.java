package com.preproject.thirdmodule.controllers;

import com.preproject.thirdmodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login", method = RequestMethod.GET)
public class LoginController {
    @Autowired
    private UserService service;
    @GetMapping
    public String getLoginView() {
        service.saveAdmin();
        return "login";
    }
}
