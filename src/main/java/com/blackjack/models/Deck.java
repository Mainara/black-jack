package com.blackjack.models;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (Suit suit: Suit.values()) {
            for (Rank rank: Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public Card getCard() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("No more cards available");
        }

        return cards.remove(0);
    }

    public List<Card> getCards() {
        return cards;
    }
}
