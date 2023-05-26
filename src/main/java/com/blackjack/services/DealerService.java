package com.blackjack.services;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DealerService {
    @Autowired
    private Dealer dealer;
    @Autowired
    private GameService gameService;

    public Map<String, Object> play() {
        if (gameService.isGameStarted()) {
            Map<String, Object> result = new HashMap<>();

            try {
                Deck deck = dealer.getDeck();

                while (dealer.shouldHit()) {
                    dealer.addCardToHand(deck.getCard());
                }
                result.put("dealerBusted", false);
            } catch (IllegalStateException e) {
                result.put("dealerBusted", true);
            }

            result.put("dealerCards", dealer.getHand().getCards());
            result.put("dealerPoints", dealer.getHandValue());
            result.put("gameIsFinished", true);
            gameService.setGameStarted(false);

            return result;

        } else {
            throw new IllegalStateException("The game has not started");
        }


    }

    public Card revealCard() {
        if (gameService.isGameStarted()) {
            try {
                Card card = dealer.revealCard();
                return card;
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalStateException("All the dealer's cards have already been revealed");
            }
        } else {
            throw new IllegalStateException("The game has not started");
        }
    }
}
