package com.preproject.thirdmodule.controllers;

import com.preproject.thirdmodule.model.User;
import com.preproject.thirdmodule.service.UserService;
import com.preproject.thirdmodule.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {

    private UserService service;

    @Autowired
    private AdminRestController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/save", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody User user) {
        if(!service.saveUser(user)) {
            return new ResponseEntity<>("Такое имя уже существует!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @GetMapping(value="/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<User>> get() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(value="/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findUserById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody User user) {
        if(!service.updateUser(id,user)) {
            return new ResponseEntity<>("Такое имя уже существует!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @DeleteMapping( "/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>("Пользователь удалён", HttpStatus.OK);
    }
}
