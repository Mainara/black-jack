package com.blackjack.useCases;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Deck;
import com.blackjack.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DealerPlayUseCase {
    @Autowired
    private DealerRepository dealerRepository;

    public Map<String, Object> play() {
        Dealer dealer = dealerRepository.getDealer();
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

        dealerRepository.saveDealer(dealer);

        result.put("dealerCards", dealer.getHand().getCards());
        result.put("dealerPoints", dealer.getHandValue());
        result.put("gameIsFinished", true);

        return result;
    }

    public Card revealCard() {
        try {
            Dealer dealer = dealerRepository.getDealer();
            Card card = dealer.revealCard();
            dealerRepository.saveDealer(dealer);
            return card;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("All the dealer's cards have already been revealed");
        }
    }
}
