package com.blackjack.services;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import com.blackjack.useCases.DealerPlayUseCase;
import com.blackjack.useCases.GameInitializationUseCase;
import com.blackjack.useCases.GameStatusUseCase;
import com.blackjack.useCases.GameStatusUseCaseTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GameServiceTests {
    @InjectMocks
    private GameService gameService;
    @Spy
    private Dealer dealer;
    @Spy
    private Player player;
    @Mock
    private GameInitializationUseCase gameInitializationUseCaseMock;
    @Mock
    private GameStatusUseCase gameStatusUseCaseMock;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenNewGame_whenCallingInitGame_thenShouldReturnFirstCards() {
        doNothing().when(dealer).clearHand();
        doNothing().when(player).clearHand();

        Map<String, Object> result = gameService.initGame();
        assertTrue(result.containsKey("dealerCards"));
        assertTrue(result.containsKey("playerCards"));
        assertTrue(result.containsKey("gameIsFinished"));

    }

    @Test
    public void givenAGame_whenCallingGetStatus_thenShouldReturnGameStatus() {
        Map<String, Object> returnGetStatus = new HashMap<>();
        returnGetStatus.put("dealerPoints", 4);
        returnGetStatus.put("playerPoints", 5);
        returnGetStatus.put("currentWinner", "player");

        when(gameStatusUseCaseMock.getStatus(false)).thenReturn(returnGetStatus);

        Map<String, Object> result = gameService.getStatus();
        assertEquals(returnGetStatus, result);
    }
}
