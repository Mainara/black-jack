package com.blackjack.useCases;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DealerPlayUseCaseTests {
    @Autowired
    private DealerPlayUseCase dealerPlayUseCase;
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

        Map<String, Object> result = dealerPlayUseCase.play();
        List<Object> dealerCards = (List<Object>) result.get("dealerCards");

        assertTrue(dealer.getHand().getCards().size() > 0);
        assertTrue(dealer.getHand().getCards().size() == dealerCards.size());
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
