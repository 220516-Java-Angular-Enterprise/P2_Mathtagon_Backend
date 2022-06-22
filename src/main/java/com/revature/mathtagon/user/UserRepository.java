package com.revature.mathtagon.user;

import com.revature.mathtagon.auth.AuthController;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.logging.Logger;

public interface UserRepository extends CrudRepository<User, String> {
    //Signing Up crypt(?3, gen_salt('bf'))
    public static final Logger logger = Logger.getLogger(UserRepository.class.getName());

    @Modifying
    @Query(value = "INSERT INTO users (userID, username, password, email, fullname, age) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true )
    void saveUser(String userID, String username, String password, String email, String fullname, Integer age);

    //To search for all users
    @Query(value = "SELECT username FROM users", nativeQuery = true)
    List<String> getAllUsernames();

    //For users to log in
    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = ?2", nativeQuery = true)
    User getUserAndPassword(String username, String password);
}
