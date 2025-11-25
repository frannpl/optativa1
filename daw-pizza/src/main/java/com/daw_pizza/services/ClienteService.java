package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Cliente;
import com.daw_pizza.persistence.repositories.ClienteRepository;
import com.daw_pizza.services.exceptions.ClienteException;
import com.daw_pizza.services.exceptions.ClienteNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
	
	
	// findById
    public Cliente findById(int idCliente) {
        if (!this.clienteRepository.existsById(idCliente)) {
            throw new ClienteNotFoundException("El cliente con id " + idCliente + " no existe.");
        }

        return this.clienteRepository.findById(idCliente).get();
    }

    // create
    public Cliente create(Cliente cliente) {
        cliente.setId(0); // Forzar nuevo registro
        return this.clienteRepository.save(cliente);
    }

    // update
    public Cliente update(Cliente cliente, int idCliente) {

        // 1. Verificar que los IDs coinciden
        if (cliente.getId() != idCliente) {
            throw new ClienteException(
                String.format("El id del body (%d) y el id del path (%d) no coinciden",
                cliente.getId(), idCliente)
            );
        }

        // 2. Verificar existencia del cliente
        if (!clienteRepository.existsById(idCliente)) {
            throw new ClienteNotFoundException("El cliente con id " + idCliente + " no existe.");
        }

        // 3. Recuperar cliente desde BD
        Cliente clienteBD = clienteRepository.findById(idCliente).get();

        // 4. Actualizar campos
        clienteBD.setNombre(cliente.getNombre());
        clienteBD.setDireccion(cliente.getDireccion());
        clienteBD.setEmail(cliente.getEmail());
        clienteBD.setTelefono(cliente.getTelefono());

        // 5. Guardar cambios
        return clienteRepository.save(clienteBD);
    }

    // delete
    public void delete(int idCliente) {
        if (!this.clienteRepository.existsById(idCliente)) {
            throw new ClienteNotFoundException("El cliente con id " + idCliente + " no existe.");
        }
        this.clienteRepository.deleteById(idCliente);
    }
    
 // Modificar solo la dirección de envío
    public Cliente modificarDireccion(int idCliente, String nuevaDireccion) {
        if (!clienteRepository.existsById(idCliente)) {
            throw new ClienteNotFoundException("El cliente con id " + idCliente + " no existe.");
        }

        Cliente cliente = clienteRepository.findById(idCliente).get();
        cliente.setDireccion(nuevaDireccion);
        return clienteRepository.save(cliente);
    }
    
    public Cliente buscarPorTelefono(String telefono) {
        Cliente cliente = clienteRepository.findByTelefono(telefono);
        if (cliente == null) {
            throw new ClienteNotFoundException("No existe cliente con teléfono " + telefono);
        }
        return cliente;
    }
}
