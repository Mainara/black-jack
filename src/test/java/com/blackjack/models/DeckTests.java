package com.blackjack.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeckTests {

    @Test
    void givenADeck_whenInstantiating_thenShouldInitiateDeckWith52Cards() {
        Deck deck = new Deck();
        assertEquals(deck.getCards().size(), 52);
    }

    @Test
    void givenADeckWithCards_whenGetCard_thenShouldReturnCard() {
        Deck deck = new Deck();
        Card card = deck.getCard();
        assertFalse(card.getUsed());
    }

    @Test
    void givenADeckWithNoCards_whenGetCard_thenShouldThrowsException() {
        Deck deck = new Deck();

        for (int i = 0; i <= 51; i++) {
            deck.getCard();
        }

        assertThrows(NoSuchElementException.class, () -> {
            deck.getCard();
        });
    }
}
