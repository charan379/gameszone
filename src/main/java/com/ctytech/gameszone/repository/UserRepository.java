package com.ctytech.gameszone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctytech.gameszone.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
