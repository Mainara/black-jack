package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DealerService {
    @Autowired
    Dealer dealer;

    public List<Card> shuffleDeck() {
        List<Card> deck = dealer.getDeck();
       Collections.shuffle(deck);
       return deck;
    }
}
