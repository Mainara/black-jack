package com.blackjack.models;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;

import java.util.List;

public class Card {
    private Rank rank;
    private Suit suit;
    private Boolean used;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.used = false;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public List<Integer> getValues() {
        return rank.getValues();
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
