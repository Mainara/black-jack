package com.blackjack.repositories;

import com.blackjack.models.Dealer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DealerRepositoryImpl implements DealerRepository {
    @Autowired
    private Dealer dealer;

    @Override
    public void saveDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public Dealer getDealer() {
        return dealer;
    }
}

