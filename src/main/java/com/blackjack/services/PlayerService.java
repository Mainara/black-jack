package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import com.blackjack.useCases.PlayerHitUseCase;
import com.blackjack.useCases.PlayerStandUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerService {
    @Autowired
    private Player player;
    @Autowired
    private  GameService gameService;
    @Autowired
    private PlayerHitUseCase playerHitUseCase;
    @Autowired
    private PlayerStandUseCase playerStandUseCase;

    public Map<String, Object> hit() {
        if (gameService.isGameStarted()) {
            return playerHitUseCase.hit(player, gameService);
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }

    public List<Card> stand() {
        if (gameService.isGameStarted()) {
            return playerStandUseCase.stand(player);
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }
}

