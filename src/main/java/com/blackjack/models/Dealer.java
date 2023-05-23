package com.blackjack.models;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Dealer extends Player {
    private final Deck deck;

    public Dealer() {
        this.deck = new Deck();
    }

    public List<Card> getDeck() {
        return deck.getCards();
    }
}
