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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw_pizza.persistence.entities.Pedido;
import com.daw_pizza.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> list(){
		return ResponseEntity.ok(this.pedidoService.findAll());
	}
	
	 @GetMapping
	    public List<Pedido> findAll() {
	        return pedidoService.findAll();
	    }

	    // Obtener pedido por id
	    @GetMapping("/{id}")
	    public Pedido findById(@PathVariable int id) {
	        return pedidoService.findById(id);
	    }

	    // Crear un pedido
	    @PostMapping
	    public Pedido create(@RequestBody Pedido pedido) {
	        return pedidoService.create(pedido);
	    }

	    // Modificar un pedido
	    @PutMapping("/{id}")
	    public Pedido update(@RequestBody Pedido pedido, @PathVariable int id) {
	        return pedidoService.update(pedido, id);
	    }

	    // Eliminar un pedido
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable int id) {
	        pedidoService.delete(id);
	    }
	    
	 // Actualizar notas del pedido
	    @PutMapping("/{id}/notas")
	    public Pedido actualizarNotas(@PathVariable int id,
	                                  @RequestParam String notas) {
	        return pedidoService.actualizarNotas(id, notas);
	    }
	}

	
