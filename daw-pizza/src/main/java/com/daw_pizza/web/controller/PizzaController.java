package com.daw_pizza.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw_pizza.persistence.entities.Pizza;
import com.daw_pizza.services.PizzaService;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	//Obtener todas las pizzas
	@GetMapping
	public List<Pizza> findAll(){
		return pizzaService.findAll();
	}
	
	//Obtener pizza por id
	@GetMapping("/{id}")
	public Pizza findById(@PathVariable int id) {
		return pizzaService.findById(id);
	}
	
	//Crear una pizza
	@PostMapping
	public Pizza create(@RequestBody Pizza pizza) {
		return pizzaService.create(pizza);
	}
	
	//Modificar pizza
	@PutMapping("/{id}")
    public Pizza update(@RequestBody Pizza pizza, @PathVariable int id) {
        return pizzaService.update(pizza, id);
    }
	
	//Eliminar tarea
	 @DeleteMapping("/{id}")
	 public void delete(@PathVariable int id) {
	        pizzaService.delete(id);
	    }
	 
	// 1) Pizzas disponibles ordenadas por precio
	    @GetMapping("/disponibles/orden-precio")
	    public List<Pizza> pizzasDisponiblesOrdenadasPorPrecio() {
	        return pizzaService.findDisponiblesOrdenadasPorPrecio();
	    }

	    // 2) Buscar pizzas por nombre (solo disponibles)
	    @GetMapping("/buscar")
	    public List<Pizza> buscarPorNombre(@RequestParam String nombre) {
	        return pizzaService.buscarPorNombreDisponible(nombre);
	    }

	    // 3) Pizzas que lleven un determinado ingrediente
	    @GetMapping("/con-ingrediente")
	    public List<Pizza> conIngrediente(@RequestParam String ingrediente) {
	        return pizzaService.buscarPorIngrediente(ingrediente);
	    }

	    // 4) Pizzas que NO lleven un determinado ingrediente
	    @GetMapping("/sin-ingrediente")
	    public List<Pizza> sinIngrediente(@RequestParam String ingrediente) {
	        return pizzaService.buscarSinIngrediente(ingrediente);
	    }

	    // 5) Actualizar precio de una pizza
	    @PutMapping("/{id}/precio")
	    public Pizza actualizarPrecio(@PathVariable int id,
	                                  @RequestParam double precio) {
	        return pizzaService.actualizarPrecio(id, precio);
	    }

	    // 6) Marcar pizza como disponible/no disponible
	    @PutMapping("/{id}/disponible")
	    public Pizza cambiarDisponibilidad(@PathVariable int id,
	                                       @RequestParam boolean disponible) {
	        return pizzaService.cambiarDisponibilidad(id, disponible);
	    }
}
