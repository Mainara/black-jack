package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Deck;
import com.blackjack.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovesService {

    @Autowired
    Dealer dealer;

    @Autowired
    Player player;

    public Card hit() {
        Card card = dealer.getDeck().getCard();
        player.addCardToHand(card);
        return card;
    }

    public Card stand() {
        Card card = dealer.getDeck().getCard();
        dealer.addCardToHand(card);
        return card;
    }
}
