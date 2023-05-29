package com.blackjack.repositories;

import com.blackjack.models.Player;

public interface PlayerRepository {
    void savePlayer(Player player);
    Player getPlayer();
}
