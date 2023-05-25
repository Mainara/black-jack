package com.blackjack.controllers;

import com.blackjack.models.Card;
import com.blackjack.services.DealerService;
import com.blackjack.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameController {

	@Autowired
	GameService gameService;

	@PostMapping("/init")
	public ResponseEntity<Map<String, List<Card>>> initGame() {
		return new ResponseEntity<>(gameService.initGame(), HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<Map<String, Integer>> gameStatus() {
		return new ResponseEntity<>(gameService.getStatus(), HttpStatus.OK);
	}
}