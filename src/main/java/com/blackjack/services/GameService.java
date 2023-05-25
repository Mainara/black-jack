package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameService {
    @Autowired
    Dealer dealer;

    @Autowired
    Player player;

    public Map<String, List<Card>> initGame() {
        // TODO: tratar caso em que o player ou dealer já inicial com > 21 pontos
        dealer.shuffleDeck();
        Card dealerHiddenCard = dealer.getDeck().getCard();
        dealerHiddenCard.setRevealed(false);

        dealer.addCardToHand(dealerHiddenCard);
        dealer.addCardToHand(dealer.getDeck().getCard());
        player.addCardToHand(dealer.getDeck().getCard());
        player.addCardToHand(dealer.getDeck().getCard());

        Map<String, List<Card>> firstCards = new HashMap<>();
        firstCards.put("dealerCards", dealer.getRevealedCards());
        firstCards.put("playerCards", player.getHand().getCards());

        return firstCards;
    }

    public Map<String, Integer> getStatus() {
        Map<String, Integer> result = new HashMap<>();
        result.put("dealerPoints", dealer.getHandValue());
        result.put("playerPoints", player.getHandValue());

        return result;
    }
}
