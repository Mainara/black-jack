package com.blackjack.useCases;

import com.blackjack.models.Card;
import com.blackjack.models.Player;
import com.blackjack.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStandUseCase {
    @Autowired
    private PlayerRepository playerRepository;
    public List<Card> stand() {
        Player player = playerRepository.getPlayer();
        return player.getHand().getCards();
    }
}
