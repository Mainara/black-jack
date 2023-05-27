package com.blackjack.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {
    AS("Às", Arrays.asList(1, 11)),
    DOIS("2", Collections.singletonList(2)),
    TRES("3", Collections.singletonList(3)),
    QUATRO("4", Collections.singletonList(4)),
    CINCO("5", Collections.singletonList(5)),
    SEIS("6", Collections.singletonList(6)),
    SETE("7", Collections.singletonList(7)),
    OITO("8", Collections.singletonList(8)),
    NOVE("9", Collections.singletonList(9)),
    DEZ("10", Collections.singletonList(10)),
    VALETE("VALETE", Collections.singletonList(11)),
    RAINHA("RAINHA", Collections.singletonList(11)),
    REI("REI", Collections.singletonList(11));

    private String label;
    private List<Integer> values;

    Rank(String label, List<Integer> values) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public List<Integer> getValues() {
        return values;
    }

}
