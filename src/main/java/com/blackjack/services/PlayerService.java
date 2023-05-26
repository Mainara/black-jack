package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerService {

    @Autowired
    Dealer dealer;

    @Autowired
    Player player;
    @Autowired
    GameService gameService;

    public Map<String, Object> hit() {
        if (gameService.isGameStarted()) {
            Map<String, Object> result = new HashMap<>();

            try {
                Card card = dealer.getDeck().getCard();
                player.addCardToHand(card);
                result.put("card", card);
                result.put("gameIsFinished", false);
            } catch (IllegalStateException e) {
                result.put("gameIsFinished", true);
                gameService.setGameStarted(false);
            }

            result.put("playerPoints", player.getHandValue());
            return result;
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }


    public List<Card> stand() {
        if (gameService.isGameStarted()) {
            return player.getHand().getCards();
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }
}
