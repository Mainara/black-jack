package com.blackjack.controllers;

import com.blackjack.models.Card;
import com.blackjack.services.DealerService;
import com.blackjack.services.MovesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/moves")
public class MovesController {

    @Autowired
    MovesService movesService;

//    @GetMapping("/deck")
//    public ResponseEntity<List<Card>> getDeck() {
//        return new ResponseEntity<>(movesService.getDeck(), HttpStatus.OK);
//    }
}
