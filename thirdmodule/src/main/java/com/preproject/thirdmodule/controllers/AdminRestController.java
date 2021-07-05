package com.preproject.thirdmodule.controllers;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.preproject.thirdmodule.model.User;
import com.preproject.thirdmodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.*;
import sun.security.provider.certpath.OCSPResponse;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {

    private UserService service;

    @Autowired
    private AdminRestController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/save", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody User user) {
        service.saveUser(user);
    }

    @GetMapping(value="/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> get() {
        return service.getAll();
    }

    @GetMapping(value="/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findUserById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Long id, @RequestBody User user) {
        service.updateUser(id, user);
    }

    @DeleteMapping( "/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
