package org.chescript.krana.common.model.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.chescript.krana.common.model.CardPhenotype;

/**
 * Simple loader for CardPhenotype definitions. Supports parsing from a JSON
 * string and loading from a file (read as string then parsed). The JSON may be
 * either a single object or an array of objects.
 */
public class CardPhenotypeLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardPhenotypeLoader.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static List<CardPhenotype> parseFromJsonString(String json) throws JsonProcessingException {
        if (json == null || json.trim().isEmpty()) return new ArrayList<>();
        LOGGER.debug("parseFromJsonString: parsing JSON of length {}", json.length());
        JsonNode root = MAPPER.readTree(json);
        List<CardPhenotype> out = new ArrayList<>();
        if (root.isArray()) {
            ArrayNode arr = (ArrayNode) root;
            for (JsonNode n : arr) {
                CardPhenotype p = MAPPER.treeToValue(n, CardPhenotype.class);
                out.add(p);
                LOGGER.debug("parseFromJsonString: parsed phenotype {}", p.getName());
            }
        } else if (root.isObject()) {
            CardPhenotype p = MAPPER.treeToValue(root, CardPhenotype.class);
            out.add(p);
            LOGGER.debug("parseFromJsonString: parsed phenotype {}", p.getName());
        }
        LOGGER.debug("parseFromJsonString: returning {} phenotypes", out.size());
        return out;
    }

    public static List<CardPhenotype> loadFromFile(Path path) throws IOException {
        LOGGER.debug("loadFromFile: reading file {}", path);
        String content = Files.readString(path);
        try {
            return parseFromJsonString(content);
        } catch (JsonProcessingException e) {
            LOGGER.error("loadFromFile: failed to parse JSON from file {}", path, e);
            throw new IOException("Failed to parse JSON from file " + path, e);
        }
    }
}
