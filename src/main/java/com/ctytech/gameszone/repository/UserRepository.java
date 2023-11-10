package com.ctytech.gameszone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctytech.gameszone.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String userName);

    @Query(value = "SELECT user_id, user_name, email, null AS password FROM User u WHERE u.user_id = ?1", nativeQuery = true)
    Optional<User> findByUserIdWithOutPassword(Integer userId);
}
