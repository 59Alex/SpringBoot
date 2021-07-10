package com.preproject.thirdmodule.service;

import com.preproject.thirdmodule.model.Role;
import com.preproject.thirdmodule.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    //  Достаём роли из DB
    @Override
    public Set<Role> getRolesFromDB(Set<Role> roles) {
        Set<Role> rolesDB = new HashSet<>();
        for(Role r : roles) {
            rolesDB.add(getRoleFromDB(r));
        }
        return rolesDB;
    }
//-------------------------------------------------------

    //  Достаём роль из DB
    @Override
    public Role getRoleFromDB(Role role) {
        Role roleDB = repository.findByRole(role.getRole());
        if(roleDB == null) {
            roleDB = repository.save(role);
            return roleDB;
        }
        return roleDB;
    }
//--------------------------------------------------------

}
