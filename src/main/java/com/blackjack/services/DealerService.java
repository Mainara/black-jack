package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class DealerService {
    @Autowired
    Dealer dealer;

    @Autowired
    Player player;

    public Map<String, List<Card>> initGame() {
        List<Card> deck = dealer.getDeck().getCards();
        Collections.shuffle(deck);

        dealer.addCardToHand(deck.get(0));
        dealer.addCardToHand(deck.get(1));

        player.addCardToHand(deck.get(2));
        player.addCardToHand(deck.get(3));

        Map<String, List<Card>> firstCards = new HashMap<>();
        firstCards.put("dealerCards", dealer.getHand().getCards());
        firstCards.put("playerCards", player.getHand().getCards());

        return firstCards;
    }

    public List<Card> getDeck() {
        List<Card> deck = dealer.getDeck().getCards();
        return deck;
    }
}
