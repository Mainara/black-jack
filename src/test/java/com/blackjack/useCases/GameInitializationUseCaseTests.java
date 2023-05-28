package com.blackjack.useCases;

import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameInitializationUseCaseTests {
    @Autowired
    private GameInitializationUseCase gameInitializationUseCase;
    @Autowired
    private Player player;
    @Autowired
    private Dealer dealer;
    @BeforeEach
    public void init() {
        dealer.clearHand();
        player.clearHand();
    }

    @Test
    public void givenNewGame_whenCallingInitGame_thenShouldReturnFirstCards() {
        assertEquals(dealer.getHand().getCards().size(), 0);
        assertEquals(player.getHand().getCards().size(), 0);

        gameInitializationUseCase.initializeGame();
        System.out.println(dealer.getHand().getCards());
        System.out.println(player.getHand().getCards());

        assertEquals(dealer.getHand().getCards().size(), 2);
        assertEquals(player.getHand().getCards().size(), 2);
    }
}
