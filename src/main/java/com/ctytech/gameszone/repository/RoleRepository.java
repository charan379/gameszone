package com.ctytech.gameszone.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctytech.gameszone.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByRoleName(String roleName);
}
