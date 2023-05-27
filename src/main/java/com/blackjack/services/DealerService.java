package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Deck;
import com.blackjack.useCases.DealerPlayUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DealerService {
    @Autowired
    private Dealer dealer;
    @Autowired
    private GameService gameService;
    @Autowired
    private DealerPlayUseCase dealerPlayUseCase;

    public Map<String, Object> play() {
        if (gameService.isGameStarted()) {
            return dealerPlayUseCase.play(dealer);
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }

    public Card revealCard() {
        if (gameService.isGameStarted()) {
            return dealerPlayUseCase.revealCard(dealer);
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }
}
