package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {
    @Autowired
    private Dealer dealer;

    @Autowired
    private Player player;
    private boolean isStarted = false;

    public Map<String, Object> initGame() {
        setGameStarted(true);
        dealer.shuffleDeck();

        Card dealerHiddenCard = dealer.getDeck().getCard();
        dealerHiddenCard.setRevealed(false);

        Map<String, Object> result = new HashMap<>();
        try {
            dealer.addCardToHand(dealerHiddenCard);
            dealer.addCardToHand(dealer.getDeck().getCard());
            player.addCardToHand(dealer.getDeck().getCard());
            player.addCardToHand(dealer.getDeck().getCard());
        } catch (IllegalStateException e) {
            setGameStarted(false);
        }

        result.put("dealerCards", dealer.getRevealedCards());
        result.put("playerCards", player.getHand().getCards());
        result.put("gameIsFinished", !isStarted);

        return result;
    }

    public Map<String, Object> getStatus() {
        if (isStarted) {
            Map<String, Object> result = new HashMap<>();
            result.put("dealerPoints", dealer.getHandValue());
            result.put("playerPoints", player.getHandValue());

            String currentWinner =  dealer.getHandValue() > player.getHandValue() ? "dealer" : "player";
            result.put("currentWinner", currentWinner);

            return result;
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }

    public void setGameStarted(boolean started) {
        isStarted = started;
    }

    public boolean isGameStarted() {
        return isStarted;
    }
}
