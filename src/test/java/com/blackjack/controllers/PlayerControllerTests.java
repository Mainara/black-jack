package com.blackjack.controllers;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.Player;
import com.blackjack.services.PlayerService;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    public void whenHit_thenStatus200() throws Exception {
        Card card1 = new Card(Rank.REI, Suit.COPAS);

        Mockito.when(playerService.hit()).thenReturn(card1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/player/hit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rank", Matchers.equalTo(card1.getRank().getLabel())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.suit", Matchers.equalTo(card1.getSuit().getLabel())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.used", Matchers.equalTo(card1.getUsed())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.revealed", Matchers.equalTo(card1.getRevealed())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.values", Matchers.equalTo(card1.getValues())));
    }

    @Test
    public void whenStand_thenStatus200() throws Exception {
        Card card = new Card(Rank.REI, Suit.COPAS);

        Mockito.when(playerService.stand()).thenReturn(Arrays.asList(card));
        Map<String, Object> cardProperties = new HashMap<>();
        cardProperties.put("rank", card.getRank().getLabel());
        cardProperties.put("suit", card.getSuit().getLabel());
        cardProperties.put("used", card.getUsed());
        cardProperties.put("revealed", card.getRevealed());
        cardProperties.put("values", card.getValues());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/player/stand"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.equalToObject(Arrays.asList(cardProperties))));

    }
}
