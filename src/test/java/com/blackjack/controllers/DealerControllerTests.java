package com.blackjack.controllers;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.services.DealerService;
import com.blackjack.services.GameService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DealerController.class)
public class DealerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealerService dealerService;

    @MockBean
    private GameService gameService;

    @AfterEach
    public void finishGame() {
        gameService.setGameStarted(false);
    }

    @Test
    public void givingAStartedGame_whenPlay_thenStatus200() throws Exception {
        gameService.setGameStarted(true);
        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.DEZ, Suit.COPAS);
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        Map<String, Object> result = new HashMap<>();
        result.put("dealerBusted", false);
        result.put("dealerCards", cards);
        result.put("gameIsFinished", true);

        Mockito.when(dealerService.play()).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/dealer/play"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dealerBusted", Matchers.equalTo(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dealerCards", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameIsFinished", Matchers.equalTo(true)));
    }

    @Test
    public void givingNotStartedGame_whenPlay_thenStatus403() throws Exception {
        Mockito.when(dealerService.play()).thenThrow(new IllegalStateException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/dealer/play"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void givingAStartedGame_whenReveal_thenStatus200() throws Exception {
        gameService.setGameStarted(true);
        Card card = new Card(Rank.REI, Suit.COPAS);

        Mockito.when(dealerService.revealCard()).thenReturn(card);
        Map<String, Object> cardProperties = new HashMap<>();
        cardProperties.put("values", card.getValues());
        cardProperties.put("revealed", card.getRevealed());
        cardProperties.put("rank", card.getRank().getLabel());
        cardProperties.put("suit", card.getSuit().getLabel());
        cardProperties.put("used", card.getUsed());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/dealer/reveal"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.equalToObject(cardProperties)));

    }

    @Test
    public void givingNotStartedGame_whenReveal_thenStatus403() throws Exception {
        Mockito.when(dealerService.revealCard()).thenThrow(new IllegalStateException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/dealer/reveal"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }



}
