package com.blackjack.services;

import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameServiceTests {
    @Autowired
    private GameService gameService;
    @Autowired
    private Dealer dealer;
    @Autowired
    private Player player;

    @BeforeEach
    public void init() {
        player.clearHand();
        dealer.clearHand();
    }

    @Test
    public void givenNewGame_whenCallingInitGame_thenShouldReturnFirstCards() {
        Map<String, Object> result = gameService.initGame();
        List<Object> dealerCards = (List<Object>) result.get("dealerCards");
        List<Object> playerCards = (List<Object>) result.get("playerCards");

        assertEquals(dealerCards.size(), 1);
        assertEquals(playerCards.size(), 2);
        assertTrue(result.containsKey("gameIsFinished"));
    }

    @Test
    public void givenAGame_whenCallingGetStatus_thenShouldReturnGameStatus() {
        gameService.initGame();
        Map<String, Object> status = gameService.getStatus();

        String currentWinner = dealer.getHandValue() > player.getHandValue() ? "dealer" : "player";

        assertEquals(dealer.getHandValue(), status.get("dealerPoints"));
        assertEquals(player.getHandValue(), status.get("playerPoints"));
        assertEquals(currentWinner, status.get("currentWinner"));

    }
}
