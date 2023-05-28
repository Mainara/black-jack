package com.blackjack.controllers;

import com.blackjack.errors.ErrorResponse;
import com.blackjack.models.Card;
import com.blackjack.services.DealerService;
import com.blackjack.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {
    @Autowired
    DealerService dealerService;

    @PostMapping("/play")
    public ResponseEntity<?> play() {
        try {
            Map<String, Object> responseData = dealerService.play();
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/reveal")
    public ResponseEntity<?> reveal() {
        try {
            Card revealedCard = dealerService.revealCard();
            return new ResponseEntity<>(revealedCard, HttpStatus.OK);
        } catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/shuffle")
    public ResponseEntity<?> shuffle() {
        try {
            List<Card> deck = dealerService.shuffle();
            return new ResponseEntity<>(deck, HttpStatus.OK);
        } catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        }
    }
}
