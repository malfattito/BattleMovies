package com.example.demo.controller;

import com.example.demo.dto.BattleDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BattleController.PATH)
public class BattleController {

    public static final String PATH = "/api/battle";

    @Autowired
    BattleService battleService;

    @GetMapping(value = "/challenge", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<BattleDTO> challenge(@RequestParam("idUser") Integer idUser) {
        BattleDTO battleDTO = battleService.challenge(idUser);

        if (battleDTO != null) {
            return ResponseEntity.ok(battleDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/answer/{idBattle}/{idMovie}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Boolean> answer(@RequestParam("idUser") Integer idUser,
                                           @PathVariable("idBattle") Integer idBattle,
                                           @PathVariable("idMovie") Integer idMovie){
        Boolean result = battleService.answer(idUser, idBattle, idMovie);
        return ResponseEntity.ok(result);

    }
}
