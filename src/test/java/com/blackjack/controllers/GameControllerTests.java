package com.blackjack.controllers;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.services.DealerService;
import com.blackjack.services.GameService;
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
@WebMvcTest(GameController.class)
class GameControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    public void whenInitGame_thenStatus200() throws Exception {
        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Card card2 = new Card(Rank.SETE, Suit.OURO);
        Card card3 = new Card(Rank.REI, Suit.ESPADAS);

        Map<String, List<Card>> firstCards = new HashMap<String, List<Card>>();
        List<Card> dealerCards = Arrays.asList(card1);
        List<Card> playerCards = Arrays.asList(card2, card3);
        firstCards.put("dealerCards", dealerCards);
        firstCards.put("playerCards", playerCards);

        Mockito.when(gameService.initGame()).thenReturn(firstCards);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/game/init"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dealerCards", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerCards", Matchers.hasSize(2)));
    }

    @Test
    public void whenGetStatus_thenStatus200() throws Exception {
        Map<String, Integer> status = new HashMap<String, Integer>();
        status.put("dealerPoints", 3);
        status.put("playerPoints", 4);

        Mockito.when(gameService.getStatus()).thenReturn(status);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/game/status"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dealerPoints", Matchers.equalTo(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerPoints", Matchers.equalTo(4)));
    }
}
