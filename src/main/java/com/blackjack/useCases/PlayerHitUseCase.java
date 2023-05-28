package com.blackjack.useCases;

import com.blackjack.models.Card;
import com.blackjack.models.Player;
import com.blackjack.repositories.PlayerRepository;
import com.blackjack.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerHitUseCase {
    @Autowired
    private PlayerRepository playerRepository;
    public Map<String, Object> hit(GameService gameService) {
        Map<String, Object> result = new HashMap<>();
        Player player = playerRepository.getPlayer();

        try {
            Card card = gameService.getCard();
            player.addCardToHand(card);
            result.put("card", card);
            result.put("gameIsFinished", false);
            playerRepository.savePlayer(player);
        } catch (IllegalStateException e) {
            result.put("gameIsFinished", true);
            gameService.setGameStarted(false);
        }

        result.put("playerPoints", player.getHandValue());
        return result;
    }
}
