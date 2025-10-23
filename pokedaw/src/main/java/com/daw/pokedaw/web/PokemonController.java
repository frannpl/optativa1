package com.daw.pokedaw.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daw.pokedaw.services.PokemonService;
import com.daw.pokedaw.services.exceptions.PokemonException;
import com.daw.pokedaw.services.exceptions.PokemonNotFoundException;
import com.daw.pokedaw.persistence.entities.Pokemon;

@RestController
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;

	// Obtener todos los pokemon
	@GetMapping("/pokemon")
	public List<Pokemon> getAllPokemon() {
		return pokemonService.findAll();
	}

	// Obtener pokemon por id
	@GetMapping("/{id}")
	public ResponseEntity<?> getPokemonById(@PathVariable int id) {
		try {
			Pokemon pokemon = pokemonService.findById(id);
			return ResponseEntity.ok(pokemon);
		} catch (PokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	// Crear nuevo pokemon
	@PostMapping
	public ResponseEntity<?> createPokemon(@RequestBody Pokemon pokemon) {
		try {
			Pokemon created = pokemonService.create(pokemon);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);
		} catch (PokemonException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	// Modificar un pokemon
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePokemon(@PathVariable int id, @RequestBody Pokemon pokemon) {
	    try {
	        Pokemon updated = pokemonService.update(pokemon, id);
	        return ResponseEntity.ok(updated);
	    } catch (PokemonNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    } catch (PokemonException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}
	
	
	// Eliminar un pokemon
	@DeleteMapping("/{idPokemon}")
	public ResponseEntity<?> delete(@PathVariable int idPokemon){
		
		try {
			this.pokemonService.delete(idPokemon);
			return ResponseEntity.ok().build();
		}
		catch (PokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

}
