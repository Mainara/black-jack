package com.blackjack.models;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HandTests {

    private Hand hand;

    @BeforeEach
    public void init() {
        hand = new Hand();
    }

    @Test
    public void givingOneCard_whenCallingGetHandValue_thenShouldReturnCardValue() {
        assertEquals(hand.getHandValue(), 0);

        Card card = new Card(Rank.REI, Suit.COPAS);
        hand.addCard(card);
        assertEquals(hand.getHandValue(), 11);
    }

    @Test
    public void givingMoreThanOneCard_whenCallingGetHandValue_thenShouldSumOfValues() {
        assertEquals(hand.getHandValue(), 0);

        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.SETE, Suit.OURO);

        hand.addCard(card1);
        hand.addCard(card2);

        assertEquals(hand.getHandValue(), 18);
    }

    @Test
    public void givingAsCard_whenCallingGetHandValue_thenAsValueShouldBe1() {
        assertEquals(hand.getHandValue(), 0);

        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.AS, Suit.OURO);

        hand.addCard(card2);
        hand.addCard(card1);

        assertEquals(hand.getHandValue(), 12);
    }


    @Test
    public void givingAsCard_whenCallingGetHandValue_thenAsValueShouldBe11() {
        assertEquals(hand.getHandValue(), 0);

        Card card1 = new Card(Rank.DEZ, Suit.COPAS);
        Card card2 = new Card(Rank.AS, Suit.OURO);

        hand.addCard(card1);
        hand.addCard(card2);

        assertEquals(hand.getHandValue(), 21);
    }

    @Test
    public void givingTwoAsCard_whenCallingGetHandValue_thenOneValueShouldBe11AndOther1() {
        assertEquals(hand.getHandValue(), 0);

        Card card1 = new Card(Rank.AS, Suit.COPAS);
        Card card2 = new Card(Rank.AS, Suit.OURO);
        Card card3 = new Card(Rank.SEIS, Suit.PAUS);

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);

        assertEquals(hand.getHandValue(), 18);
    }
}
