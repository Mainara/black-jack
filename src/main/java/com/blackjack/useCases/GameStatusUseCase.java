package com.blackjack.useCases;

import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameStatusUseCase {
    public Map<String, Object> getStatus(Dealer dealer, Player player, boolean isStarted) {
        if (isStarted) {
            Map<String, Object> result = new HashMap<>();
            result.put("dealerPoints", dealer.getHandValue());
            result.put("playerPoints", player.getHandValue());

            String currentWinner = dealer.getHandValue() > player.getHandValue() ? "dealer" : "player";
            result.put("currentWinner", currentWinner);

            return result;
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }
}
