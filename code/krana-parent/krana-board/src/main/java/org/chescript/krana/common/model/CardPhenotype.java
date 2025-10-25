package org.chescript.krana.common.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonCreator
    public CardPhenotype(
            @JsonProperty("name") String name,
            @JsonProperty("label") String label,
            @JsonProperty("description") String description,
            @JsonProperty("strength") int strength,
            @JsonProperty("willpower") int willpower,
            @JsonProperty("lore") int lore,
            @JsonProperty("cardImage") CardImage cardImage) {
        this.name = name;
        this.label = label;
        this.description = description;
        this.strength = strength;
        this.willpower = willpower;
        this.lore = lore;
        this.cardImage = cardImage;
    }
}
