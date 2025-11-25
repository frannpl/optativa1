package com.daw_pizza.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.daw_pizza.persistence.entities.Pizza;
import com.daw_pizza.persistence.repositories.PizzaRepository;
import com.daw_pizza.services.exceptions.PizzaException;
import com.daw_pizza.services.exceptions.PizzaNotFoundException;

public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	// findAll
		public List<Pizza> findAll() {
			return this.pizzaRepository.findAll();
		}

		// findById
		public Pizza findById(int idTarea) {
			if (!this.pizzaRepository.existsById(idTarea)) {
				throw new PizzaNotFoundException("La tarea con id " + idTarea + " no existe. ");
			}

			return this.pizzaRepository.findById(idTarea).get();
		}

		// create
		public Pizza create(Pizza pizza) {
		

			pizza.setId(0);
		

			return this.pizzaRepository.save(pizza);
		}

		// update
		public Pizza update(Pizza pizza, int idPizza) {

		    // 1. Comprobar que el id del body y del path coinciden
		    if (pizza.getId() != idPizza) {
		        throw new PizzaException(
		            String.format("El id del body (%d) y el id del path (%d) no coinciden",
		            pizza.getId(), idPizza)
		        );
		    }

		    // 2. Comprobar que la pizza existe
		    if (!pizzaRepository.existsById(idPizza)) {
		        throw new PizzaNotFoundException("La pizza con id " + idPizza + " no existe.");
		    }

		    // 3. Recuperar pizza de la BD
		    Pizza pizzaBD = pizzaRepository.findById(idPizza).get();  // método simple, como pediste

		    // 4. Actualizar todos los campos (por ahora no hay restricciones)
		    pizzaBD.setNombre(pizza.getNombre());
		    pizzaBD.setDescripcion(pizza.getDescripcion());
		    pizzaBD.setPrecio(pizza.getPrecio());
		    pizzaBD.setDisponible(pizza.isDisponible());
		    pizzaBD.setVegana(pizza.isVegana());
		    pizzaBD.setVegetariana(pizza.isVegetariana());

		    // 5. Guardar cambios
		    return pizzaRepository.save(pizzaBD);
		}


		// delete
		public void delete(int idPizza) {
			if (!this.pizzaRepository.existsById(idPizza)) {
				throw new PizzaNotFoundException("La tarea no existe");
			}
			this.pizzaRepository.deleteById(idPizza);
		}

		// Pizzas disponibles ordenadas por precio
		public List<Pizza> findDisponiblesOrdenadasPorPrecio() {
		    return pizzaRepository.findByDisponibleTrueOrderByPrecioAsc();
		}

		// Buscar pizzas por nombre (solo disponibles)
		public List<Pizza> buscarPorNombreDisponible(String nombre) {
		    return pizzaRepository.findByNombreContainingIgnoreCaseAndDisponibleTrue(nombre);
		}

		// Pizzas que llevan un determinado ingrediente (en la descripción)
		public List<Pizza> buscarPorIngrediente(String ingrediente) {
		    return pizzaRepository.findByDescripcionContainingIgnoreCase(ingrediente);
		}

		// Pizzas que NO llevan un determinado ingrediente
		public List<Pizza> buscarSinIngrediente(String ingrediente) {
		    List<Pizza> todas = pizzaRepository.findAll();
		    return todas.stream()
		            .filter(p -> p.getDescripcion() == null ||
		                         !p.getDescripcion().toLowerCase().contains(ingrediente.toLowerCase()))
		            .toList();
		}

		// Actualizar precio de una pizza
		public Pizza actualizarPrecio(int idPizza, double nuevoPrecio) {
		    if (!pizzaRepository.existsById(idPizza)) {
		        throw new PizzaNotFoundException("La pizza con id " + idPizza + " no existe.");
		    }

		    Pizza pizza = pizzaRepository.findById(idPizza).get();
		    pizza.setPrecio(nuevoPrecio);
		    return pizzaRepository.save(pizza);
		}

		// Marcar pizza como disponible / no disponible
		public Pizza cambiarDisponibilidad(int idPizza, boolean disponible) {
		    if (!pizzaRepository.existsById(idPizza)) {
		        throw new PizzaNotFoundException("La pizza con id " + idPizza + " no existe.");
		    }

		    Pizza pizza = pizzaRepository.findById(idPizza).get();
		    pizza.setDisponible(disponible);
		    return pizzaRepository.save(pizza);
		}

}
