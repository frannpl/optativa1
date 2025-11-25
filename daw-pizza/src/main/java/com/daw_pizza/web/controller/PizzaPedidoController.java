package com.daw_pizza.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw_pizza.persistence.entities.PizzaPedido;
import com.daw_pizza.services.PizzaPedidoService;

@RestController
@RequestMapping("/pizzaPedidos")
public class PizzaPedidoController {

	@Autowired
	private PizzaPedidoService pizzaPedidoService;
	
	@GetMapping
	public ResponseEntity<List<PizzaPedido>> list(){
		return ResponseEntity.ok(this.pizzaPedidoService.findAll());
	}
	
	  @GetMapping
	    public List<PizzaPedido> findAll() {
	        return pizzaPedidoService.findAll();
	    }

	    // Obtener un registro pizza_pedido por id
	    @GetMapping("/{id}")
	    public PizzaPedido findById(@PathVariable int id) {
	        return pizzaPedidoService.findById(id);
	    }

	    // Crear un nuevo registro pizza_pedido
	    @PostMapping
	    public PizzaPedido create(@RequestBody PizzaPedido pizzaPedido) {
	        return pizzaPedidoService.create(pizzaPedido);
	    }

	    // Modificar un registro pizza_pedido existente
	    @PutMapping("/{id}")
	    public PizzaPedido update(@RequestBody PizzaPedido pizzaPedido, @PathVariable int id) {
	        return pizzaPedidoService.update(pizzaPedido, id);
	    }

	    // Eliminar un registro pizza_pedido
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable int id) {
	        pizzaPedidoService.delete(id);
	    }
}