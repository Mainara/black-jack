package com.blackjack.services;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PlayerServiceTest {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private Player player;

    @BeforeEach
    public void init() {
        player.clearHand();
    }

    @Test
    public void whenCallingHit_thenShouldAddACardToPlayer() {
        playerService.hit();
        assertTrue(player.getHand().getCards().size() == 1);
        assertTrue(player.getHand().getHandValue() > 0);
    }

    @Test
    public void whenCallingStand_thenShouldReturnPlayerCards() {
        Card card1 = new Card(Rank.AS, Suit.COPAS);
        Card card2 = new Card(Rank.QUATRO, Suit.PAUS);

        player.addCardToHand(card1);
        player.addCardToHand(card2);

        List<Card> cards = playerService.stand();

        assertTrue(cards.size() == 2);
    }
}
