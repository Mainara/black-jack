package com.blackjack.services;

import com.blackjack.models.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovesServiceTests {

    @Autowired
    MovesService movesService;

    @Test
    void givenAGame_whenCallingHit_thenShouldAddCardToPlayer() {

    }
}
