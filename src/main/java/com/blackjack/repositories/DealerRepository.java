package com.blackjack.repositories;

import com.blackjack.models.Dealer;

public interface DealerRepository {
    void saveDealer(Dealer dealer);
    Dealer getDealer();
}

