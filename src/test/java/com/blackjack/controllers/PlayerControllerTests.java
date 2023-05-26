package com.blackjack.controllers;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.services.GameService;
import com.blackjack.services.PlayerService;
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

    @MockBean
    private GameService gameService;

    @AfterEach
    public void finishGame() {
        gameService.setGameStarted(false);
    }

    @Test
    public void givingAStartedGame_whenHit_thenStatus200() throws Exception {
        gameService.setGameStarted(true);
        Card card1 = new Card(Rank.REI, Suit.COPAS);
        Map<String, Object> result = new HashMap<>();
        result.put("gameIsFinished", false);
        result.put("card", card1);

        Mockito.when(playerService.hit()).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/player/hit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameIsFinished", Matchers.equalTo(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.card", Matchers.notNullValue()));
    }

    @Test
    public void givingNotStartedGame_whenHit_thenStatus403() throws Exception {
        Mockito.when(playerService.hit()).thenThrow(new IllegalStateException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/player/hit"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void givingAStartedGame_whenStand_thenStatus200() throws Exception {
        gameService.setGameStarted(true);
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

    @Test
    public void givingNotStartedGame_whenStand_thenStatus403() throws Exception {
        Mockito.when(playerService.stand()).thenThrow(new IllegalStateException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/player/stand"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }
}
