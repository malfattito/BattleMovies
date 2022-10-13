package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BattleDTO implements Serializable {

    private static final long serialVersionUID = 2L;

    private Integer id;
    private UserDTO userDTO;
    private MovieDTO movieDTO1;
    private MovieDTO movieDTO2;
    private Integer match;

    public BattleDTO(Integer id, UserDTO userDTO, MovieDTO movieDTO1, MovieDTO movieDTO2, Integer match) {
        this.id = id;
        this.userDTO = userDTO;
        this.movieDTO1 = movieDTO1;
        this.movieDTO2 = movieDTO2;
        this.match = match;
    }
}
