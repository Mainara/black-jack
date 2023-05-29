package com.blackjack.useCases;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DealerPlayUseCaseTests {
    @Autowired
    private DealerPlayUseCase dealerPlayUseCase;
    @Mock
    private GameService gameService;
    @Autowired
    private Dealer dealer;

    @BeforeEach
    public void init() {
        dealer.clearHand();
    }

    @Test
    public void whenCallingPlay_thenDealerShouldAddCardToHand() {
        assertTrue(dealer.getHand().getCards().size() == 0);
        Card card = new Card(Rank.AS, Suit.COPAS);
        dealer.addCardToHand(card);
        when(gameService.isGameStarted()).thenReturn(true);

        Map<String, Object> status = new HashMap<>();
        status.put("dealerPoints", dealer.getHandValue());
        status.put("playerPoints", 0);
        String currentWinner = "dealer";
        status.put("currentWinner", currentWinner);

        when(gameService.getStatus()).thenReturn(status);

        dealerPlayUseCase.play(gameService);

        assertTrue(dealer.getHand().getCards().size() > 0);
        assertTrue(dealer.getHandValue() > (int) status.get("dealerPoints"));
    }

    @Test
    public void whenCallingReveal_thenShouldRevealDealerCard() {
        Card card = new Card(Rank.REI, Suit.ESPADAS);
        card.setRevealed(false);

        dealer.addCardToHand(card);
        Card revealedCard = dealerPlayUseCase.revealCard();
        assertTrue(revealedCard.getRank().getLabel() == card.getRank().getLabel());
    }

    @Test
    public void givingNoCardToReveal_whenCallingReveal_thenShouldThrowsException() {
        Card card = new Card(Rank.REI, Suit.ESPADAS);
        dealer.addCardToHand(card);

        assertThrows(IllegalStateException.class, () -> {
            dealerPlayUseCase.revealCard();
        });
    }
}
