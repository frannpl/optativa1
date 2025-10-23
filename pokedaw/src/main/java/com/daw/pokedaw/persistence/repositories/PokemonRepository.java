package com.daw.pokedaw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.pokedaw.persistence.entities.Pokemon;

public interface PokemonRepository extends ListCrudRepository<Pokemon, Integer>{

	List<Pokemon> findByNumeroPokedex(int numeroPokedex);
	boolean existsByNumeroPokedex(int numeroPokedex);
}
