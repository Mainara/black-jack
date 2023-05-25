package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Deck;
import com.blackjack.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class DealerService {
    @Autowired
    Dealer dealer;

    public List<Card> play() {
        Deck deck = dealer.getDeck();

        while (dealer.shouldHit()) {
            dealer.addCardToHand(deck.getCard());
        }

        return dealer.getHand().getCards();
    }

    public Card revealCard() {
        try {

            Card card = dealer.revealCard();
            return card;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("all the dealer's cards have already been revealed");
        }
    }
}
