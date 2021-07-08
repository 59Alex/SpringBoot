package com.preproject.thirdmodule.service;

import com.preproject.thirdmodule.model.User;
import com.sun.istack.NotNull;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAll();
    User findUserById(long id);
    boolean saveUser(@NotNull User user);
    boolean updateUser(long id, @NotNull User user);
    void deleteUser(long id);
}
