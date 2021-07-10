package com.preproject.thirdmodule.repository;

import com.preproject.thirdmodule.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByRole(@Param("role") String role);
}
