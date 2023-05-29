package com.blackjack.useCases;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Deck;
import com.blackjack.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShuffleUseCase {
    @Autowired
    private DealerRepository dealerRepository;

    public List<Card> shuffle() {
        Dealer dealer = dealerRepository.getDealer();
        List<Card> deck = dealer.shuffleDeck();
        dealerRepository.saveDealer(dealer);
        return deck;
    }
}
