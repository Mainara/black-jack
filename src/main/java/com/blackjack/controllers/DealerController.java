package com.blackjack.controllers;

import com.blackjack.models.Card;
import com.blackjack.services.DealerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {

	@Autowired
	DealerService dealerService;

	@GetMapping("/init")
	public ResponseEntity<Map<String, List<Card>>> initGame() {
		return new ResponseEntity<>(dealerService.initGame(), HttpStatus.OK);
	}

//	@GetMapping("/deck")
//	public ResponseEntity<List<Card>> getDeck() {
//		return new ResponseEntity<>(dealerService.getDeck(), HttpStatus.OK);
//	}

}