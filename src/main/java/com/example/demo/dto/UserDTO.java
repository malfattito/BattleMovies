package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    private Integer id;
    private String login;
    private Long points;

    public UserDTO(Integer id, String login, Long points) {
        this.id = id;
        this.login = login;
        this.points = points;
    }
}
