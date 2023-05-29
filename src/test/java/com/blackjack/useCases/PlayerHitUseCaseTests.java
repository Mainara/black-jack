package com.blackjack.useCases;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.Player;
import com.blackjack.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlayerHitUseCaseTests {
    @Autowired
    private Player player;
    @Autowired
    private PlayerHitUseCase playerHitUseCase;
    @Mock
    private GameService gameService;
    @BeforeEach
    public void init() {
        player.clearHand();
    }

    @Test
    public void whenCallingHit_thenShouldAddACardToPlayer() {
        Card card = new Card(Rank.DOIS, Suit.COPAS);
        when(gameService.getCard()).thenReturn(card);

        playerHitUseCase.hit(gameService);

        assertTrue(player.getHand().getCards().size() == 1);
        assertTrue(player.getHand().getHandValue() == 2);
    }
}
