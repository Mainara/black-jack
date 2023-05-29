package com.blackjack.models;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PlayerTests {
    private Player player;

    @BeforeEach
    public void init() {
        player = new Player();
    }

    @Test
    public void givenACard_whenCallingAddCardToHand_thenShouldAddCard() {
        Card card1 = new Card(Rank.SETE, Suit.COPAS);
        Card card2 = new Card(Rank.DOIS, Suit.PAUS);
        Card card3 = new Card(Rank.DEZ, Suit.ESPADAS);

        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);

        assertTrue(player.getHand().getCards().size() == 3);
        assertTrue(player.getHandValue() == 19);
    }

    @Test
    public void givenAHandWithMoreThan21Points_whenCallingAddCardToHand_thenShouldThrowsException() {
        Card card1 = new Card(Rank.SETE, Suit.COPAS);
        Card card2 = new Card(Rank.DOIS, Suit.PAUS);
        Card card3 = new Card(Rank.DEZ, Suit.ESPADAS);
        Card card4 = new Card(Rank.QUATRO, Suit.PAUS);

        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);

        assertThrows(IllegalStateException.class, () -> {
            player.addCardToHand(card4);
        });
    }
}
