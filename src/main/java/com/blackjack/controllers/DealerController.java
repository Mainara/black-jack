package com.blackjack.controllers;

import com.blackjack.models.Card;
import com.blackjack.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {
    @Autowired
    DealerService dealerService;

    @PostMapping("/play")
    public ResponseEntity<List<Card>> play() {
        return new ResponseEntity<>(dealerService.play(), HttpStatus.OK);
    }

    @PostMapping("/reveal")
    public ResponseEntity<Card> reveal() {
        return new ResponseEntity<>(dealerService.revealCard(), HttpStatus.OK);
    }
}
