package org.chescript.krana.common.model;

import lombok.Data;

@Data
public class CardPhenotype {
    private final String name;
    private final String label;
    private final String description;
    private final int strength; // formerly offence
    private final int willpower; // formerly defence
    private final int lore;
    private final CardImage cardImage;
    // Add more fields as needed

    // Constructor, no setters for immutability
}
