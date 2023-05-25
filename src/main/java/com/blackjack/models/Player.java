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

    public void clearHand() {
        getHand().getCards().clear();
    }

    public void addCardToHand(Card card) {
        hand.addCard(card);

        if (hand.getHandValue() > 21) {
            throw new IllegalStateException("The sum points exceeded 21 points");
        }

        card.setUsed(true);
    }

    public int getHandValue() {
        return hand.getHandValue();
    }
}
