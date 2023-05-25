package com.blackjack.models;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public void shuffleDeck() {
        Collections.shuffle(deck.getCards());
    }

    public List<Card> getRevealedCards() {
        List<Card> filteredCards = filterByRevealed(true);

        return filteredCards;
    }

    public Card revealCard() {
        List<Card> filteredCards = filterByRevealed(false);

        Card card = filteredCards.get(0);
        card.setRevealed(true);
        return card;
    }

    private List<Card> filterByRevealed(boolean condition) {
        Predicate<Card> byRevealed = card -> card.getRevealed() == condition;

        List<Card> filteredCards = getHand().getCards().stream()
                .filter(byRevealed)
                .collect(Collectors.toList());
        return filteredCards;
    }
}
