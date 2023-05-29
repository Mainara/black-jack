package com.blackjack.enums;

public enum Suit {
    COPAS("COPAS"),
    OURO("OURO"),
    PAUS("PAUS"),
    ESPADAS("ESPADAS");

    private String label;

    Suit(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
