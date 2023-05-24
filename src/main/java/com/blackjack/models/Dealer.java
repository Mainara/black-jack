package com.blackjack.models;

import com.blackjack.enums.Rank;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Dealer extends Player {
    private final Deck deck;

    public Dealer() {
        this.setName("dealer");
        this.deck = new Deck();
    }

    public boolean shouldHit() {
        return getHandValue() < 17;
    }

    public Deck getDeck() {
        return deck;
    }
}
