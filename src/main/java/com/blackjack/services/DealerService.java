package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.useCases.DealerPlayUseCase;
import com.blackjack.useCases.ShuffleUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DealerService {
    @Autowired
    private GameService gameService;
    @Autowired
    private DealerPlayUseCase dealerPlayUseCase;
    @Autowired
    private ShuffleUseCase shuffleUseCase;
    public Map<String, Object> play() {
        if (gameService.isGameStarted()) {
            return dealerPlayUseCase.play(gameService);
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }

    public Card revealCard() {
        if (gameService.isGameStarted()) {
            return dealerPlayUseCase.revealCard();
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }

    public List<Card> shuffle() {
        if (gameService.isGameStarted()) {
            return shuffleUseCase.shuffle();
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }
}
