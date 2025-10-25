package org.chescript.krana.common.model.repository.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.chescript.krana.common.model.CardPhenotype;
import org.chescript.krana.common.model.io.CardPhenotypeLoader;
import org.chescript.krana.common.model.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple in-memory repository implementation that can be populated from a JSON
 * string or a file. Lives in `krana-board` as a convenience implementation; for
 * production you may implement a repository that reads from DB or remote API.
 */
public class InMemoryCardRepository implements CardRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryCardRepository.class);

    private final List<CardPhenotype> items = new ArrayList<>();

    public InMemoryCardRepository() {
    }

    public void loadFromJsonString(String json) {
        LOGGER.debug("loadFromJsonString: loading JSON of length {}", json == null ? 0 : json.length());
        try {
            List<CardPhenotype> parsed = CardPhenotypeLoader.parseFromJsonString(json);
            items.clear();
            items.addAll(parsed);
            LOGGER.debug("loadFromJsonString: loaded {} phenotypes", parsed.size());
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to parse JSON", e);
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    public void loadFromFile(Path path) {
        LOGGER.debug("loadFromFile: loading from file {}", path);
        try {
            List<CardPhenotype> parsed = CardPhenotypeLoader.loadFromFile(path);
            items.clear();
            items.addAll(parsed);
            LOGGER.debug("loadFromFile: loaded {} phenotypes from {}", parsed.size(), path);
        } catch (IOException e) {
            LOGGER.error("Failed to load file {}", path, e);
            throw new RuntimeException("Failed to load file", e);
        }
    }

    @Override
    public Optional<CardPhenotype> findByName(String name) {
        if (name == null) return Optional.empty();
        return items.stream().filter(p -> name.equals(p.getName())).findFirst();
    }

    @Override
    public List<CardPhenotype> findAll() {
        return Collections.unmodifiableList(items);
    }
}
