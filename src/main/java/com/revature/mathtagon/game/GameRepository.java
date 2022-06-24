package com.revature.mathtagon.game;

import com.revature.mathtagon.game.dtos.requests.NewScoreRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {

    @Modifying
    @Query(value = "INSERT INTO games (score,gametype,userID) VALUES (?1,?2,?3)",nativeQuery = true)
    void record(Integer score, Integer gameType, String userID);

    @Modifying
    @Query(value = "INSERT INTO games (gametype,userID) VALUES (?1,?2)", nativeQuery = true)
    void createGame(Integer gameType,String userID);


}
