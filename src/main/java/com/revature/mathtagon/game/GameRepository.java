package com.revature.mathtagon.game;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {

    @Modifying
    @Query(value = "INSERT INTO game SET score = ? WHERE userID = ?")
    void addPoints(Integer score, String userID);
}
