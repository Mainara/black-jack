package com.blackjack.controllers;

import com.blackjack.models.Card;
import com.blackjack.services.DealerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {

	@Autowired
	DealerService dealerService;

	@GetMapping("/shuffle")
	public ResponseEntity<List<Card>> shuffleDeck() {
		return new ResponseEntity<>(dealerService.shuffleDeck(), HttpStatus.OK);
	}

}