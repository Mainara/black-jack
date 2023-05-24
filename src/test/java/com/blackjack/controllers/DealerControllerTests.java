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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DealerController.class)
class DealerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealerService dealerService;

    @Test
    public void whenInitGame_thenStatus200() throws Exception {
        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.SETE, Suit.OURO);
        Card card3 = new Card(Rank.REI, Suit.ESPADAS);
        Card card4 = new Card(Rank.AS, Suit.ESPADAS);

        Map<String, List<Card>> firstCards = new HashMap<String, List<Card>>();
        List<Card> dealerCards = Arrays.asList(card1, card2);
        List<Card> playerCards = Arrays.asList(card3, card4);
        firstCards.put("dealerCards", dealerCards);
        firstCards.put("playerCards", playerCards);

        Mockito.when(dealerService.initGame()).thenReturn(firstCards);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/dealer/init"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dealerCards", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerCards", Matchers.hasSize(2)));
    }
}
