package com.blackjack.useCases;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import com.blackjack.repositories.DealerRepository;
import com.blackjack.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameInitializationUseCase {
    @Autowired
    private DealerRepository dealerRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public void initializeGame() {
        Dealer dealer = dealerRepository.getDealer();
        Player player = playerRepository.getPlayer();

        Card dealerHiddenCard = dealer.getDeck().getCard();
        dealerHiddenCard.setRevealed(false);

        dealer.addCardToHand(dealerHiddenCard);
        dealer.addCardToHand(dealer.getDeck().getCard());
        player.addCardToHand(dealer.getDeck().getCard());
        player.addCardToHand(dealer.getDeck().getCard());

        dealerRepository.saveDealer(dealer);
        playerRepository.savePlayer(player);
    }
}

