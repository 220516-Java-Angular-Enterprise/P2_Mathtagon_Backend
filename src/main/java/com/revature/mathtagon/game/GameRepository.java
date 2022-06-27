package com.revature.mathtagon.game;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, String> {

    @Modifying
    @Query(value = "INSERT INTO games (gametype,score,userID) VALUES (?1,?2,?3)",nativeQuery = true)
    void record(Integer gameType, Integer score, String userID);



    @Query(value = "SELECT u.username, SUM(m.score) FROM games m INNER JOIN users u USING (userID) GROUP BY username ORDER BY sum(score) DESC LIMIT 5",nativeQuery = true)
    List<Object[]> topFive();


}
