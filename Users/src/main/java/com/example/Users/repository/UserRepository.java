package com.example.Users.repository;

import com.example.Users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // CRUD methods

    @Query("select s from User s where s.email=?1")
    Optional<User> findUserByEMail(String email);
}

