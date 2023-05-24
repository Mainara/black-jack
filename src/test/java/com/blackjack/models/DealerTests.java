package com.blackjack.models;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DealerTests {

    Dealer dealer;

    @BeforeEach
    void init() {
        dealer = new Dealer();
    }

    @Test
    void whenCallingAddCardToHand_thenShouldAddCard() {
        assertEquals(dealer.getHand().getCards().size(), 0);

        Card card = new Card(Rank.REI, Suit.COPAS);
        dealer.addCardToHand(card);
        assertEquals(dealer.getHand().getCards().size(), 1);
    }

    @Test
    void givenHandValueLessThan17_whenCallingShouldHit_thenShouldReturnTrue() {
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
    void givenHandValueGraterOrEqualThan17_whenCallingShouldHit_thenShouldReturnFalse() {
        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.SEIS, Suit.COPAS);

        dealer.addCardToHand(card1);
        dealer.addCardToHand(card2);
        assertFalse(dealer.shouldHit());

        Card card3 = new Card(Rank.SETE, Suit.COPAS);
        dealer.addCardToHand(card3);
        assertFalse(dealer.shouldHit());

    }
}
