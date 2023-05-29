package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import com.blackjack.useCases.GameInitializationUseCase;
import com.blackjack.useCases.GameStatusUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {
    @Autowired
    private Dealer dealer;
    @Autowired
    private Player player;
    @Autowired
    private GameInitializationUseCase gameInitializationUseCase;
    @Autowired
    private GameStatusUseCase gameStatusUseCase;

    private boolean isStarted = false;

    public Map<String, Object> initGame() {
        Map<String, Object> result = new HashMap<>();
        try {
            startGame();
        } catch (IllegalStateException e) {
            result.put("status", gameStatusUseCase.getStatus(isStarted));
            setGameStarted(false);
        }

        result.put("dealerCards", dealer.getRevealedCards());
        result.put("playerCards", player.getHand().getCards());
        result.put("gameIsFinished", !isStarted);

        return result;
    }

    public Map<String, Object> getStatus() {
        return gameStatusUseCase.getStatus(isStarted);
    }

    private void startGame() {
        setGameStarted(true);
        dealer.clearHand();
        player.clearHand();
        dealer.resetDeck();
        dealer.shuffleDeck();
        gameInitializationUseCase.initializeGame();
    }

    public void setGameStarted(boolean started) {
        isStarted = started;
    }

    public boolean isGameStarted() {
        return isStarted;
    }

    public Card getCard() {
        return dealer.getDeck().getCard();
    }
}
