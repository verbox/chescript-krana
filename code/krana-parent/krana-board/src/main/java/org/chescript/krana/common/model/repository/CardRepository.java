package org.chescript.krana.common.model.repository;

import java.util.List;
import java.util.Optional;

import org.chescript.krana.common.model.CardPhenotype;

/**
 * Contract for a repository of card phenotypes. Implementations may load from
 * JSON, database, or remote API. This interface lives in `krana-board` so the
 * board logic can depend on a stable contract while IO lives outside or in a
 * simple loader implementation.
 */
public interface CardRepository {
    Optional<CardPhenotype> findByName(String name);
    List<CardPhenotype> findAll();
}

