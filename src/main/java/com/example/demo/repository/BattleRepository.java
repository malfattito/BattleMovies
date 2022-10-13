package com.example.demo.repository;

import com.example.demo.entity.Battle;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BattleRepository extends CrudRepository<Battle, Integer> {

    @Query(value = "SELECT max(match) FROM Battle WHERE user.id = ?1")
    Integer lastMatch(Integer idUser);

    @Query(value = "SELECT COUNT(b) FROM Battle b WHERE b.user = ?1")
    Long countBattleByUser(User user);

    @Query(value = "SELECT COUNT(b) FROM Battle b WHERE b.user = ?1 AND b.result = true ")
    Long countBattleWinByUser(User user);
}