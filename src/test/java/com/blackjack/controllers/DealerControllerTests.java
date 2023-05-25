package com.blackjack.controllers;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.services.DealerService;
import org.hamcrest.Matchers;
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

    @Test
    public void whenPlay_thenStatus200() throws Exception {
        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.DEZ, Suit.COPAS);
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        Mockito.when(dealerService.play()).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/dealer/play"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void whenReveal_thenStatus200() throws Exception {
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
}
