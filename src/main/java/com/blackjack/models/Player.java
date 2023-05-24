package com.blackjack.models;

import org.springframework.stereotype.Component;

@Component
public class Player {
    private String name;
    private Hand hand;

    public Player() {
        this.name = "player";
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void addCardToHand(Card card) {
        hand.addCard(card);
        card.setUsed(true);
    }

    public int getHandValue() {
        return hand.getHandValue();
    }
}
