package com.daw.pokedaw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.pokedaw.persistence.entities.Pokemon;
import com.daw.pokedaw.persistence.repositories.PokemonRepository;
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

//	public Pokemon create(Pokemon pokemon) {
//		
//		if(pokemon == null) {
//			throw new IllegalArgumentException("El Pokemon no puede ser nulo");
//		}
//		if(pokemon.getNombre() == null || pokemon.getNombre().isEmpty()) {
//			throw new PokemonException("El nombgre del Pokemon es obligatorio");
//		}
//		return null;
//	}
	
	
}
