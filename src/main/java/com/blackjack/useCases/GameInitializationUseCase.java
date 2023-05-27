package com.blackjack.useCases;

import com.blackjack.models.Card;
import com.blackjack.models.Dealer;
import com.blackjack.models.Player;
import org.springframework.stereotype.Service;

@Service
public class GameInitializationUseCase {
    private final Dealer dealer;
    private final Player player;

    public GameInitializationUseCase(Dealer dealer, Player player) {
        this.dealer = dealer;
        this.player = player;
    }

    public void initializeGame() {
        Card dealerHiddenCard = dealer.getDeck().getCard();
        dealerHiddenCard.setRevealed(false);

        dealer.addCardToHand(dealerHiddenCard);
        dealer.addCardToHand(dealer.getDeck().getCard());
        player.addCardToHand(dealer.getDeck().getCard());
        player.addCardToHand(dealer.getDeck().getCard());
    }
}

