package com.example.demo.controller;

import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MovieController.PATH)
public class MovieController {

    public static final String PATH = "/api/movie";

    @Autowired
    MovieService movieService;

    @GetMapping("/webscraping")
    private void webscraping(){
        movieService.webscraping();
    }
}
