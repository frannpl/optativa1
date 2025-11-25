package com.daw_pizza.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw_pizza.persistence.entities.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    // Pizzas disponibles ordenadas por precio (ascendente)
    List<Pizza> findByDisponibleTrueOrderByPrecioAsc();

    // Buscar pizzas por nombre (solo disponibles)
    List<Pizza> findByNombreContainingIgnoreCaseAndDisponibleTrue(String nombre);

    // Buscar pizzas que lleven un ingrediente (en la descripción)
    List<Pizza> findByDescripcionContainingIgnoreCase(String ingrediente);
}
