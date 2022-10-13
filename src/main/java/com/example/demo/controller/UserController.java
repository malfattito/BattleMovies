package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.MovieService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.PATH)
public class UserController {

    public static final String PATH = "/api/user";

    @Autowired
    UserService userService;

    @GetMapping("/ranking")
    private ResponseEntity<List<UserDTO>> ranking(){
        List<UserDTO> ranking = userService.ranking();
        return ResponseEntity.ok(ranking);

    }
    
    @PutMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<UserDTO> create(@RequestParam("login") String login,
                                           @RequestParam("senha") String senha) {
        UserDTO userDTO = userService.createUser(login, senha);

        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
