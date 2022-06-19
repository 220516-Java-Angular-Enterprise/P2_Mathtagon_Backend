package com.revature.mathtagon.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, String> {
    @Modifying
    @Query(value = "INSERT INTO users (userID, username, password, email, fullname, age) VALUES (?1, ?2, crypt(?3, gen_salt('bf')), ?4, ?5, ?6)", nativeQuery = true )
    void saveUser(String userID, String username, String password, String email, String fullname, Integer age);

    @Query(value = "SELECT username FROM users", nativeQuery = true)
    List<String> getAllUsers();

    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = crypt(?2, password)", nativeQuery = true)
    Users getUserAndPassword(String username, String password);
}
