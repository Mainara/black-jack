package com.blackjack.services;

import com.blackjack.models.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DealerServiceTests {

    @Autowired
    DealerService dealerService;

    @Test
    void givenNewGame_whenCallingInitGame_thenShouldReturnFirstCards() {
        Map<String, List<Card>> firstCards = dealerService.initGame();
        assertEquals(firstCards.get("dealerCards").size(), 2);
        assertEquals(firstCards.get("playerCards").size(), 2);
    }
}
