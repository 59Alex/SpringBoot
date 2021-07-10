package com.preproject.thirdmodule.service;

import com.preproject.thirdmodule.model.Role;
import com.preproject.thirdmodule.model.User;
import com.preproject.thirdmodule.repository.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Logger;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private RoleService roleService;
    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder encoder,
                           RoleService roleService) {
        this.roleService = roleService;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }
    public UserServiceImpl() {}

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

    public User findUserById(long id) { return userRepository.findById(id).get(); }

    public boolean saveUser(@NotNull User user) {
        //проверка на уникальность поля username
        try {
            loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ex) {
            user.setRoles(roleService.getRolesFromDB(user.getRoles()));
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean updateUser(long id, User user){
        //проверка на уникальность поля username
        User userBD = null;
        try {
            userBD = userRepository.findById(id).get();
            if(userBD.getUsername().equals(user.getUsername())) {
                throw new UsernameNotFoundException("");
            }
            loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ex) {
            userBD.setEmail(user.getEmail());
            userBD.setFirstName(user.getFirstName());
            userBD.setLastName(user.getLastName());
            userBD.setPassword(encoder.encode(user.getPassword()));
            userBD.setAge(user.getAge());
            userBD.setRoles(roleService.getRolesFromDB(user.getRoles()));
            return true;
        }
        return false;
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveAdmin() {
        try {
            loadUserByUsername("admin@mail.ru");
        } catch (UsernameNotFoundException ignored) {
            User admin = new User();
            admin.setFirstName("admin");
            admin.setPassword(encoder.encode("123"));
            admin.setAge(12);
            admin.setEmail("admin@mail.ru");
            admin.setLastName("admin");
            Role role = new Role("ROLE_ADMIN");
            Role role2 = new Role("ROLE_USER");
            Set<Role> setRole = new HashSet<>();
            setRole.add(role);
            setRole.add(role2);
            admin.setRoles(roleService.getRolesFromDB(setRole));
            userRepository.save(admin);
        }

    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if(user == null) {
            logger.info("dont load");
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
