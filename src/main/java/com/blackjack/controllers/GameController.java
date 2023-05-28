package com.blackjack.controllers;

import com.blackjack.errors.ErrorResponse;
import com.blackjack.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@PostMapping("/init")
	public ResponseEntity<Map<String, Object>> initGame() {
		return new ResponseEntity<>(gameService.initGame(), HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<?> gameStatus() {
		try {
			Map<String, Object> gameStatus = gameService.getStatus();
			return new ResponseEntity<>(gameStatus, HttpStatus.OK);
		} catch (IllegalStateException e) {
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
		}
	}

}