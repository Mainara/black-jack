package com.blackjack.repositories;

import com.blackjack.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {
    @Autowired
    private Player player;

    @Override
    public void savePlayer(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
