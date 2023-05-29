package com.blackjack.services;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Deck;
import com.blackjack.models.Hand;
import com.blackjack.useCases.DealerPlayUseCase;
import com.blackjack.useCases.ShuffleUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DealerServiceTests {

    @InjectMocks
    private DealerService dealerService;
    @Mock
    private DealerPlayUseCase dealerPlayUseCaseMock;
    @Mock
    private ShuffleUseCase shuffleUseCase;
    @Autowired
    private Dealer dealer;
    @MockBean
    private GameService gameService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        dealer.clearHand();
    }

    @Test
    public void givenAStartedGame_whenCallingPlay_thenDealerShouldPlay() {
        when(gameService.isGameStarted()).thenReturn(true);
        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.DEZ, Suit.COPAS);
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        Map<String, Object> playResult = new HashMap<>();
        playResult.put("dealerBusted", false);
        playResult.put("dealerCards", cards);
        playResult.put("gameIsFinished", true);

        when(dealerPlayUseCaseMock.play(gameService)).thenReturn(playResult);
        Map<String, Object> result = dealerService.play();
        assertEquals(playResult, result);
    }

    @Test
    public void givenNotStartedGame_whenCallingPlay_thenDealerShouldThrowException() {
        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.DOIS, Suit.COPAS);

        dealer.addCardToHand(card1);
        dealer.addCardToHand(card2);

        assertThrows(IllegalStateException.class, () -> {
            dealerService.play();
        });
    }

    @Test
    public void whenCallingReveal_thenShouldRevealDealerCard() {
        when(gameService.isGameStarted()).thenReturn(true);

        Card card1 = new Card(Rank.REI, Suit.COPAS);
        card1.setRevealed(false);

        when(dealerPlayUseCaseMock.revealCard()).thenReturn(card1);
        Card revealResult = dealerService.revealCard();
        assertEquals(revealResult, card1);
    }

    @Test
    public void givenNotStartedGame_whenCallingReveal_thenShouldThrowsException() {
        Card card = new Card(Rank.REI, Suit.ESPADAS);
        dealer.addCardToHand(card);

        assertThrows(IllegalStateException.class, () -> {
            dealerService.revealCard();
        });
    }

    @Test
    public void givingAStartedGame_whenCallingShuffle_thenShouldShuffle() {
        when(gameService.isGameStarted()).thenReturn(true);

        Card card1 = new Card(Rank.REI, Suit.COPAS);

        when(shuffleUseCase.shuffle()).thenReturn(Arrays.asList(card1));
        List<Card> shuffleResult = dealerService.shuffle();
        assertEquals(shuffleResult, Arrays.asList(card1));
    }

    @Test
    public void givenNotStartedGame_whenCallingShuffle_thenShouldThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            dealerService.shuffle();
        });
    }

}
