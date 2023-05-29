package com.blackjack.services;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.Player;
import com.blackjack.useCases.PlayerHitUseCase;
import com.blackjack.useCases.PlayerStandUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;


@SpringBootTest
public class PlayerServiceTest {
    @InjectMocks
    private PlayerService playerService;
    @Autowired
    private Player player;
    @Mock
    private GameService gameServiceMock;
    @Mock
    private PlayerHitUseCase playerHitUseCase;
    @Mock
    private PlayerStandUseCase playerStandUseCase;
    @BeforeEach
    public void init() {
        player.clearHand();
    }

    @Test
    public void givingAStartedGame_whenCallingHit_thenShouldAddACardToPlayer() {
        when(gameServiceMock.isGameStarted()).thenReturn(true);

        Map<String, Object> returnHit = new HashMap<>();
        Card card = new Card(Rank.DOIS, Suit.COPAS);
        returnHit.put("card", card);
        returnHit.put("playerPoints", 2);
        returnHit.put("gameIsFinished", false);

        when(playerHitUseCase.hit(gameServiceMock)).thenReturn(returnHit);
        Map<String, Object> result = playerService.hit();
        assertEquals(returnHit, result);
    }

    @Test
    public void givingNotStartedGame_whenCallingHit_thenShouldThrowException() {
        when(gameServiceMock.isGameStarted()).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> {
            playerService.hit();
        });
    }

    @Test
    public void whenCallingStand_thenShouldReturnPlayerCards() {
        when(gameServiceMock.isGameStarted()).thenReturn(true);
        Card returnStand = new Card(Rank.DOIS, Suit.COPAS);

        when(playerStandUseCase.stand()).thenReturn(Arrays.asList(returnStand));
        List<Card> result = playerService.stand();
        assertEquals(Arrays.asList(returnStand), result);
    }
}
