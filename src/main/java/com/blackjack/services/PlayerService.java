package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    Dealer dealer;

    @Autowired
    Player player;

    public Card hit() {
        Card card = dealer.getDeck().getCard();
        player.addCardToHand(card);

        return card;
    }

    public List<Card> stand() {
        return player.getHand().getCards();
    }
}
