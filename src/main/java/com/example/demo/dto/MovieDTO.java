package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MovieDTO implements Serializable {

    private Integer id;
    private String name;
    private String urlImage;
    private double rating;

    public MovieDTO(Integer id, String name, String urlImage, double rating) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.rating = rating;
    }
}
