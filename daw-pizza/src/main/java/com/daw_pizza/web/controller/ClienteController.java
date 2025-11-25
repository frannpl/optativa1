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

import com.daw_pizza.persistence.entities.Cliente;
import com.daw_pizza.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> list(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
	
	  // Obtener todos los clientes
    @GetMapping
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    // Obtener cliente por id
    @GetMapping("/{id}")
    public Cliente findById(@PathVariable int id) {
        return clienteService.findById(id);
    }

    // Crear un cliente
    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.create(cliente);
    }

    // Modificar un cliente
    @PutMapping("/{id}")
    public Cliente update(@RequestBody Cliente cliente, @PathVariable int id) {
        return clienteService.update(cliente, id);
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        clienteService.delete(id);
    }
    
    @PutMapping("/{id}/direccion")
    public Cliente actualizarDireccion(@PathVariable int id,
                                       @RequestParam String direccion) {
        return clienteService.modificarDireccion(id, direccion);
    }
    
 // Buscar cliente por teléfono
    @GetMapping("/buscar-telefono")
    public Cliente buscarPorTelefono(@RequestParam String telefono) {
        return clienteService.buscarPorTelefono(telefono);
    }
	
}