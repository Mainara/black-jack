package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

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
        Map<String, List<Card>> firstCards = gameService.initGame();
        assertEquals(firstCards.get("dealerCards").size(), 1);
        assertEquals(firstCards.get("playerCards").size(), 2);
    }

    @Test
    public void givenAGame_whenCallingGetStatus_thenShouldReturnGameStatus() {
        gameService.initGame();
        Map<String, Integer> status = gameService.getStatus();

        assertEquals(dealer.getHandValue(), status.get("dealerPoints"));
        assertEquals(player.getHandValue(), status.get("playerPoints"));
    }
}
