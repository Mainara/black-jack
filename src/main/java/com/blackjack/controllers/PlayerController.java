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
import java.util.Map;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping("/hit")
    public ResponseEntity<Map<String, Object>> hit() {
        try {
            return new ResponseEntity<>(playerService.hit(), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/stand")
    public ResponseEntity<List<Card>> stand() {
        try {
            return new ResponseEntity<>(playerService.stand(), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
