package com.blackjack.useCases;

import com.blackjack.models.Card;
import com.blackjack.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStandUseCase {
    public List<Card> stand(Player player) {
        return player.getHand().getCards();
    }
}
