package com.daw.pokedaw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.pokedaw.persistence.entities.Pokemon;
import com.daw.pokedaw.persistence.entities.Pokeball;
import com.daw.pokedaw.persistence.entities.Tipo;
import com.daw.pokedaw.persistence.repositories.PokemonRepository;
import com.daw.pokedaw.services.exceptions.PokemonException;
import com.daw.pokedaw.services.exceptions.PokemonNotFoundException;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> findAll() {
        return this.pokemonRepository.findAll();
    }

    public Pokemon findById(int idPokemon) {
        return this.pokemonRepository.findById(idPokemon)
                .orElseThrow(() -> new PokemonNotFoundException("El Pokémon con id " + idPokemon + " no existe."));
    }

    public Pokemon create(Pokemon pokemon) {

        if (pokemon == null) {
            throw new IllegalArgumentException("El Pokémon no puede ser nulo.");
        }

        if (pokemon.getNombre() == null || pokemon.getNombre().isEmpty()) {
            throw new PokemonException("El nombre del Pokémon es obligatorio.");
        }

        if (pokemon.getCapturado() == null) {
            pokemon.setCapturado(Pokeball.POKEBALL);
        }

        if (pokemon.getTipo1() == null) {
            throw new PokemonException("El Pokémon debe tener al menos un tipo.");
        }

        if (pokemon.getTipo2() != null && pokemon.getTipo1() == pokemon.getTipo2()) {
            throw new PokemonException("El tipo1 y el tipo2 no pueden ser iguales.");
        }

        pokemon.setFechaCaptura(LocalDate.now());

        return this.pokemonRepository.save(pokemon);
    }

    public Pokemon update(Pokemon pokemon, int id) {

        if (pokemon.getId() != id) {
            throw new PokemonException(String.format(
                    "El id del body (%d) y el id del path (%d) no coinciden", pokemon.getId(), id));
        }

        Pokemon pokemonDB = this.findById(id);

        if (pokemon.getFechaCaptura() != null && !pokemon.getFechaCaptura().equals(pokemonDB.getFechaCaptura())) {
            throw new PokemonException("No se puede modificar la fecha de captura.");
        }

        if (pokemon.getCapturado() != null && pokemon.getCapturado() != pokemonDB.getCapturado()) {
            throw new PokemonException("No se puede modificar el tipo de pokeball con este endpoint.");
        }

        pokemonDB.setNombre(pokemon.getNombre());
        pokemonDB.setNumeroPokedex(pokemon.getNumeroPokedex());
        pokemonDB.setTipo1(pokemon.getTipo1());
        pokemonDB.setTipo2(pokemon.getTipo2());

        return this.pokemonRepository.save(pokemonDB);
    }

    public void delete(int idPokemon) {
        if (!this.pokemonRepository.existsById(idPokemon)) {
            throw new PokemonNotFoundException("El Pokémon no existe.");
        }
        this.pokemonRepository.deleteById(idPokemon);
    }

    public Pokemon findByNumeroPokedex(int numeroPokedex) {
        return this.pokemonRepository.findByNumeroPokedex(numeroPokedex)
                .stream()
                .findFirst()
                .orElseThrow(() -> new PokemonNotFoundException(
                        "El Pokémon con número de Pokédex " + numeroPokedex + " no existe."));
    }

    public List<Pokemon> findByDate(LocalDate desde, LocalDate hasta) {
        if (desde == null || hasta == null) {
            throw new PokemonException("Debes indicar ambas fechas.");
        }
        if (desde.isAfter(hasta)) {
            throw new PokemonException("La fecha 'desde' no puede ser posterior a 'hasta'.");
        }

        return this.pokemonRepository.findByDate(desde, hasta);
    }

    public List<Pokemon> findByTipo(Tipo tipo) {
        List<Pokemon> lista = pokemonRepository.findByTipo1Tipo2(tipo, tipo);

        if (lista.isEmpty()) {
            throw new PokemonNotFoundException("No se han encontrado Pokémon del tipo " + tipo);
        }

        return lista;
    }

    public Pokemon cambiarTipo(int idPokemon, Tipo nuevoTipo, int tipoSlot) {
        Pokemon pokemon = findById(idPokemon);

        if (tipoSlot != 1 && tipoSlot != 2) {
            throw new PokemonException("El parámetro tipoSlot debe ser 1 o 2.");
        }

        if ((tipoSlot == 1 && nuevoTipo == pokemon.getTipo2())
                || (tipoSlot == 2 && nuevoTipo == pokemon.getTipo1())) {
            throw new PokemonException("El tipo1 y tipo2 no pueden ser iguales.");
        }

        if (tipoSlot == 1) {
            pokemon.setTipo1(nuevoTipo);
        } else {
            pokemon.setTipo2(nuevoTipo);
        }

        return pokemonRepository.save(pokemon);
    }
}
