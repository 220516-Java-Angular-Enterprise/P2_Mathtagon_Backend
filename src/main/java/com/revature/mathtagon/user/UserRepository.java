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
    @Query(value = "INSERT INTO users (userID, username, password, email, fullname, age) VALUES (?1, ?2, crypt(?3, gen_salt('bf')), ?4, ?5, ?6)", nativeQuery = true )
    void saveUser(String userID, String username, String password, String email, String fullname, Integer age);

    //To search for all users
    @Query(value = "SELECT username FROM users", nativeQuery = true)
    List<String> getAllUsernames();
    @Query(value = "SELECT email FROM users", nativeQuery = true)
    List<String> getAllEmails();

    //Gets a specified user's history purpose is for games
    @Query(value = "SELECT * FROM users WHERE username = ?", nativeQuery = true)
    User getUserHistory(String username);

    //For guest to select user history
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    User getAllHistory();
    //For users to log in
    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = crypt(?2, password)", nativeQuery = true)
    User getUserAndPassword(String username, String password);

    @Query(value = "SELECT * FROM users WHERE username = ?", nativeQuery = true)
    User getByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE userid = ?", nativeQuery = true)
    User getByID(String id);
}
