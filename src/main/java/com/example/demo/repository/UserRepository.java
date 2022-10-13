package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u ORDER BY points DESC")
    List<User> findUserOrderByPoints();
}