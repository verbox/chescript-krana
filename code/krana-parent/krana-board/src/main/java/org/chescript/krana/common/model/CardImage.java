package org.chescript.krana.common.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CardImage {
    private final String imageId; // e.g., "mickey-mouse-001"
    private final String filename; // e.g., "mickey-mouse-001.png"
    // Optionally, add more metadata if needed

    // Constructor, getters, etc.
    @JsonCreator
    public CardImage(
            @JsonProperty("imageId") String imageId,
            @JsonProperty("filename") String filename) {
        this.imageId = imageId;
        this.filename = filename;
    }
}
