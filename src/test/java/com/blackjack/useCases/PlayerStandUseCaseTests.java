package com.blackjack.useCases;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Player;
import com.blackjack.models.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class PlayerStandUseCaseTests {
    @Autowired
    private Player player;
    @Autowired
    private PlayerStandUseCase playerStandUseCase;
    @BeforeEach
    public void init() {
        player.clearHand();
    }

    @Test
    public void whenCallingStand_thenShouldReturnPlayerCards() {
        Card card1 = new Card(Rank.AS, Suit.COPAS);
        Card card2 = new Card(Rank.QUATRO, Suit.PAUS);

        player.addCardToHand(card1);
        player.addCardToHand(card2);

        List<Card> cards = playerStandUseCase.stand();

        assertTrue(cards.size() == 2);
    }
}
