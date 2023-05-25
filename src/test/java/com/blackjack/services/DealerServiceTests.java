package com.blackjack.services;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Deck;
import com.blackjack.models.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DealerServiceTests {

    @Autowired
    private DealerService dealerService;
    @Autowired
    private Dealer dealer;

    @BeforeEach
    public void init() {
        dealer.clearHand();
    }

    @Test
    public void whenCallingPlay_thenDealerShouldAddCardToHand() {
        assertTrue(dealer.getHand().getCards().size() == 0);

        List<Card> addedCards = dealerService.play();

        assertTrue(dealer.getHand().getCards().size() > 0);
        assertTrue(dealer.getHand().getCards().size() == addedCards.size());
    }

    @Test
    public void whenCallingReveal_thenShouldRevealDealerCard() {
        Card card = new Card(Rank.REI, Suit.ESPADAS);
        card.setRevealed(false);

        dealer.addCardToHand(card);

        Card revealedCard = dealerService.revealCard();
        assertTrue(revealedCard.getRank().getLabel() == card.getRank().getLabel());

    }

    @Test
    public void givingNoCardToReveal_whenCallingReveal_thenShouldThrowsException() {
        Card card = new Card(Rank.REI, Suit.ESPADAS);
        dealer.addCardToHand(card);

        assertThrows(IllegalStateException.class, () -> {
            dealerService.revealCard();
        });
    }
}
