package com.blackjack.useCases;

import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GameStatusUseCaseTests {
    @Autowired
    private GameStatusUseCase gameStatusUseCase;
    @Autowired
    private GameInitializationUseCase gameInitializationUseCase;
    @Autowired
    private Player player;
    @Autowired
    private Dealer dealer;

    @Test
    public void givenAStartedGame_whenCallingGetStatus_thenShouldReturnGameStatus() {
        try {
            gameInitializationUseCase.initializeGame();

            Map<String, Object> status = gameStatusUseCase.getStatus(true);

            String currentWinner = dealer.getHandValue() > player.getHandValue() ? "dealer" : "player";

            assertEquals(dealer.getHandValue(), status.get("dealerPoints"));
            assertEquals(player.getHandValue(), status.get("playerPoints"));
            assertEquals(currentWinner, status.get("currentWinner"));
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage() == "The sum points exceeded 21 points");
        }
    }

    @Test
    public void givenNotStartedGame_whenCallingGetStatus_thenShouldThrowException() {
        assertThrows(IllegalStateException.class, () -> {
            gameStatusUseCase.getStatus(false);
        });


    }
}
