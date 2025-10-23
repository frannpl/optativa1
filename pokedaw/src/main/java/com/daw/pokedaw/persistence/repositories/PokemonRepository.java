package com.daw.pokedaw.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.pokedaw.persistence.entities.Pokemon;
import com.daw.pokedaw.persistence.entities.Tipo;

public interface PokemonRepository extends ListCrudRepository<Pokemon, Integer>{

	List<Pokemon> findByNumeroPokedex(int numeroPokedex);
	boolean existsByNumeroPokedex(int numeroPokedex);
	
	List<Pokemon> findByDate(LocalDate desde, LocalDate hasta);
	
    List<Pokemon> findByTipo1Tipo2(Tipo tipo1, Tipo tipo2);

}
