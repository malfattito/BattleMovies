package com.example.demo.service;

import com.example.demo.dto.BattleDTO;
import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Battle;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.repository.BattleRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BattleService {

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    public BattleDTO challenge(Integer idUser) {

        Integer lastMatch = battleRepository.lastMatch(idUser);

        if (lastMatch == null) {
            lastMatch = 0;
        }

        Optional<Movie> movie1 = movieRepository.findById(57);
        Optional<Movie> movie2 = movieRepository.findById(48);
        Optional<User> user = userRepository.findById(idUser);
        Battle battle = new Battle(user.get(), movie1.get(), movie2.get(), lastMatch + 1);

        battleRepository.save(battle);

        return createBatleDTOByBatle(battle);

    }

    private BattleDTO createBatleDTOByBatle(Battle battle) {

        UserDTO userDTO = new UserDTO(battle.getUser().getId(), battle.getUser().getLogin(), battle.getUser().getPoints());
        MovieDTO movieDTO1 = new MovieDTO(battle.getMovie1().getId(), battle.getMovie1().getName(), battle.getMovie1().getUrlImage(), battle.getMovie1().getRating());
        MovieDTO movieDTO2 = new MovieDTO(battle.getMovie2().getId(), battle.getMovie2().getName(), battle.getMovie2().getUrlImage(), battle.getMovie2().getRating());

        return new BattleDTO(battle.getId(), userDTO, movieDTO1, movieDTO2, battle.getMatch());

    }

    public Boolean answer(Integer idUser, Integer idBattle, Integer idMovie) {

        Optional<Battle> battle = battleRepository.findById(idBattle);
        Optional<User> user = userRepository.findById(idUser);

        if(battle.isPresent()){
            Boolean result = checkAnswer(battle.get(),idMovie);

            if(result){
                battle.get().setResult(true);

                user.get().setPoints(user.get().getPoints() + 1);
                userRepository.save(user.get());

            }else{
                battle.get().setResult(false);
            }
            battleRepository.save(battle.get());

            return result;
        }
        return false;
    }

    private boolean checkAnswer(Battle battle, Integer idMovieAnswer){
        if (battle.getMovie1().getRating() > battle.getMovie2().getRating()) {
            if (battle.getMovie1().getId() == idMovieAnswer) {
                return true;
            }
        } else {
            if (battle.getMovie2().getId() == idMovieAnswer) {
                return true;
            }
        }
        return false;

    }
}
