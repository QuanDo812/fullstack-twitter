package com.twitter.demo.repository;

import com.twitter.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u WHERE u.fullName LIKE %?1% OR u.email LIKE %?1% ")
    public List<User> searchUser(String query);
}
