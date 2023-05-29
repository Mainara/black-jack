package com.blackjack.models;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DealerTests {

    private Dealer dealer;

    @BeforeEach
    public void init() {
        dealer = new Dealer();
    }

    @Test
    public void givenHandValueLessThan17_whenCallingShouldHit_thenShouldReturnTrue() {
        assertEquals(dealer.getHand().getCards().size(), 0);
        assertTrue(dealer.shouldHit());

        Card card1 = new Card(Rank.REI, Suit.COPAS);
        dealer.addCardToHand(card1);
        assertTrue(dealer.shouldHit());

        Card card2 = new Card(Rank.CINCO, Suit.COPAS);
        dealer.addCardToHand(card2);
        assertTrue(dealer.shouldHit());
    }

    @Test
    public void givenHandValueGraterOrEqualThan17_whenCallingShouldHit_thenShouldReturnFalse() {
        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.SEIS, Suit.COPAS);

        dealer.addCardToHand(card1);
        dealer.addCardToHand(card2);
        assertFalse(dealer.shouldHit());

        Card card3 = new Card(Rank.QUATRO, Suit.COPAS);
        dealer.addCardToHand(card3);
        assertFalse(dealer.shouldHit());

    }

    @Test
    public void givenAHandOfCards_whenCallingGetRevealedCards_thenShouldReturnRevealedCards() {
        Card card1 = new Card(Rank.DOIS, Suit.COPAS);
        Card card2 = new Card(Rank.DOIS, Suit.COPAS);

        dealer.addCardToHand(card1);
        dealer.addCardToHand(card2);
        assertTrue(dealer.getRevealedCards().size() == 2);

        Card card3 = new Card(Rank.SETE, Suit.COPAS);
        card3.setRevealed(false);
        dealer.addCardToHand(card3);
        assertTrue(dealer.getRevealedCards().size() == 2);

        Card card4 = new Card(Rank.SEIS, Suit.PAUS);
        dealer.addCardToHand(card4);
        assertTrue(dealer.getRevealedCards().size() == 3);

    }

    @Test
    public void givenAHandOfCards_whenCallingRevealCard_thenShouldReturnHiddenCard() {
        Card card1 = new Card(Rank.SETE, Suit.COPAS);
        card1.setRevealed(false);
        Card card2 = new Card(Rank.AS, Suit.ESPADAS);
        dealer.addCardToHand(card1);
        dealer.addCardToHand(card2);

        Card revealedCard = dealer.revealCard();
        assertEquals(revealedCard.getRank().getLabel(), card1.getRank().getLabel());
    }

    @Test
    public void givingNoCardToReveal_whenCallingRevealCard_thenShouldThrowsException() {
        Card card = new Card(Rank.REI, Suit.ESPADAS);
        dealer.addCardToHand(card);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            dealer.revealCard();
        });
    }
}
