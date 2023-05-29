package com.blackjack.models;

import com.blackjack.enums.Rank;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getHandValue() {
        int value = 0;
        int numAs = 0;

        for (Card card : cards) {
            value += card.getRank().getValues().get(0);

            if (card.getRank() == Rank.AS) {
                numAs++;
            }
        }

        while ((value + 10) <= 21 && numAs > 0) {
            value += 10;
            numAs--;
        }

        return value;
    }

    public List<Card> getCards() {
        return cards;
    }
}
