package com.daw.pokedaw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.pokedaw.persistence.entities.Pokemon;
import com.daw.pokedaw.persistence.repositories.PokemonRepository;
import com.daw.pokedaw.services.exceptions.PokemonException;
import com.daw.pokedaw.services.exceptions.PokemonNotFoundException;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;
	
	// findAll
	public List<Pokemon> findAll(){
		return this.pokemonRepository.findAll();
		
	}
	
	// findById
	public Pokemon findById(int idPokemon) {
		if(!this.pokemonRepository.existsById(idPokemon)) {
			throw new PokemonNotFoundException("El pokemon con id " + idPokemon + " no existe.");
		}
		return this.pokemonRepository.findById(idPokemon).get();
	}

	public Pokemon create(Pokemon pokemon) {
		
		if(pokemon == null) {
			throw new IllegalArgumentException("El Pokemon no puede ser nulo");
		}
		if(pokemon.getNombre() == null || pokemon.getNombre().isEmpty()) {
			throw new PokemonException("El nombre del Pokemon es obligatorio");
		}
		return this.pokemonRepository.save(pokemon);
	}

	public Pokemon update(Pokemon pokemon, int id) {

		if (pokemon.getId() != id) {
	        throw new PokemonException(
	                String.format("El id del body (%d) y el id del path (%d) no coinciden", pokemon.getId(), id));
	    }
	    

		if (!this.pokemonRepository.existsById(id)) {
	        throw new PokemonNotFoundException("El pokemon con id " + id + " no existe.");
	    }
	    

		Pokemon pokemonDB = this.findById(id);
	    

		if (pokemon.getFechaCaptura() != null) {
	        throw new PokemonException("No se puede modificar la fecha de captura.");
	    }
	    

		pokemonDB.setNombre(pokemon.getNombre());
	    pokemonDB.setNumeroPokedex(pokemon.getNumeroPokedex());
	    pokemonDB.setTipo1(pokemon.getTipo1());
	    pokemonDB.setTipo2(pokemon.getTipo2());
	    

	    return this.pokemonRepository.save(pokemonDB);
	}
	
	// Eliminar pokemon
	public void delete(int idPokemon) {
		
		if(!this.pokemonRepository.existsById(idPokemon)) {
			throw new PokemonNotFoundException("La tarea no existe");
		}
			
		this.pokemonRepository.deleteById(idPokemon);
		
	}
	
	
	// Buscar pokemon segun nº pokedex
	public Pokemon findByNumeroPokedex(int numeroPokedex) {
		if(!this.pokemonRepository.existsByNumeroPokedex(numeroPokedex)) {
			throw new PokemonNotFoundException("El Pokemon con número de Pokedex " + numeroPokedex);
		}
		
		return this.pokemonRepository.findByNumeroPokedex(numeroPokedex).get(0);
	}

	
	
}
