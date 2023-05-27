package com.blackjack.useCases;

import com.blackjack.models.Card;
import com.blackjack.models.Player;
import com.blackjack.services.GameService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerHitUseCase {
    public Map<String, Object> hit(Player player, GameService gameService) {
        Map<String, Object> result = new HashMap<>();

        try {
            Card card = gameService.getCard();
            player.addCardToHand(card);
            result.put("card", card);
            result.put("gameIsFinished", false);
        } catch (IllegalStateException e) {
            result.put("gameIsFinished", true);
            gameService.setGameStarted(false);
        }

        result.put("playerPoints", player.getHandValue());
        return result;
    }
}
