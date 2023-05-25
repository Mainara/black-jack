package com.blackjack.controllers;

import com.blackjack.models.Card;
import com.blackjack.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping("/hit")
    public ResponseEntity<Card> hit() {
        return new ResponseEntity<>(playerService.hit(), HttpStatus.OK);
    }

    @PostMapping("/stand")
    public ResponseEntity<List<Card>> hand() {
        return new ResponseEntity<>(playerService.stand(), HttpStatus.OK);
    }
}
