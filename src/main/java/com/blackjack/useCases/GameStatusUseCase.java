package com.blackjack.useCases;

import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import com.blackjack.repositories.DealerRepository;
import com.blackjack.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameStatusUseCase {
    @Autowired
    private DealerRepository dealerRepository;
    @Autowired
    private PlayerRepository playerRepository;
    public Map<String, Object> getStatus(boolean isStarted) {
        if (isStarted) {
            Dealer dealer = dealerRepository.getDealer();
            Player player = playerRepository.getPlayer();

            Map<String, Object> result = new HashMap<>();
            result.put("dealerPoints", dealer.getHandValue());
            result.put("playerPoints", player.getHandValue());

            String currentWinner = dealer.getHandValue() > player.getHandValue() ? "dealer" : "player";
            result.put("currentWinner", currentWinner);

            dealerRepository.saveDealer(dealer);
            playerRepository.savePlayer(player);

            return result;
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }
}
