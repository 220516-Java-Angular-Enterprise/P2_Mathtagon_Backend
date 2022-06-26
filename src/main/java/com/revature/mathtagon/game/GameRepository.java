package com.revature.mathtagon.game;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {

    @Modifying
    @Query(value = "INSERT INTO games (gametype,score,userID) VALUES (?1,?2,?3)",nativeQuery = true)
    void record(Integer gameType, Integer score, String userID);


}
