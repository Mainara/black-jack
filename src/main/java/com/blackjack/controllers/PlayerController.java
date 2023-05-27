package com.blackjack.controllers;

import com.blackjack.errors.ErrorResponse;
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
    public ResponseEntity<?> hit() {
        try {
            Map<String, Object> hitResult = playerService.hit();
            return new ResponseEntity<>(hitResult, HttpStatus.OK);
        } catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        }
    }


    @PostMapping("/stand")
    public ResponseEntity<?> stand() {
        try {
            List<Card> standResult = playerService.stand();
            return new ResponseEntity<>(standResult, HttpStatus.OK);
        } catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        }
    }

}
