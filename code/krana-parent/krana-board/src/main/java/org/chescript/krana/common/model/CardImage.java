package org.chescript.krana.common.model;

import lombok.Data;

@Data
public class CardImage {
    private final String imageId; // e.g., "mickey-mouse-001"
    private final String filename; // e.g., "mickey-mouse-001.png"
    // Optionally, add more metadata if needed

    // Constructor, getters, etc.
}
