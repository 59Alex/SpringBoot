package com.preproject.thirdmodule.repository;


import com.preproject.thirdmodule.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByEmail(@Param("email") String email);
}
