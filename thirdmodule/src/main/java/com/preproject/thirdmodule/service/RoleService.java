package com.preproject.thirdmodule.service;

import com.preproject.thirdmodule.model.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getRolesFromDB(Set<Role> roles);
    Role getRoleFromDB(Role role);
}
