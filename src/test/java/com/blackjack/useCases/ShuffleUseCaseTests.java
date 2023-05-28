package com.blackjack.useCases;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.repositories.DealerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class ShuffleUseCaseTests {
    @Autowired
    ShuffleUseCase shuffleUseCase;
    @Autowired
    DealerRepository dealerRepository;

    @Test
    public void whenCallingStand_thenShouldReturnPlayerCards() {
        Dealer dealer = dealerRepository.getDealer();

        List<Card> initDeck = dealer.getDeck().getCards();
        List<Card> original = new ArrayList<>(initDeck);
        shuffleUseCase.shuffle();
        List<Card> shuffledDeck = dealer.getDeck().getCards();

        assertNotEquals(original, shuffledDeck);
    }
}
