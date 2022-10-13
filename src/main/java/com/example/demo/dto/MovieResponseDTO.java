package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class MovieResponseDTO {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Poster")
    private String urlImage;

    @JsonProperty("imdbRating")
    private String rating;

    public MovieResponseDTO() {
    }

    public MovieResponseDTO(String title, String urlImage, String rating) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
    }


}
