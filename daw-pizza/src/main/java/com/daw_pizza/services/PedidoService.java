package com.daw_pizza.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Pedido;
import com.daw_pizza.persistence.repositories.PedidoRepository;
import com.daw_pizza.services.exceptions.PedidoException;
import com.daw_pizza.services.exceptions.PedidoNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;	
	
	public List<Pedido> findAll(){
		return this.pedidoRepository.findAll();
	}
	
	// findById
    public Pedido findById(int idPedido) {
        if (!this.pedidoRepository.existsById(idPedido)) {
            throw new PedidoNotFoundException("El pedido con id " + idPedido + " no existe.");
        }

        return this.pedidoRepository.findById(idPedido).get();
    }

 // create
    public Pedido create(Pedido pedido) {
        pedido.setId(0);
        pedido.setFecha(LocalDateTime.now());   // fecha/hora del sistema
        pedido.setTotal(0.0);                   // total inicial a 0
        return this.pedidoRepository.save(pedido);
    }

    // update
    public Pedido update(Pedido pedido, int idPedido) {

        // 1. Verificar que los IDs coinciden
        if (pedido.getId() != idPedido) {
            throw new PedidoException(
                String.format("El id del body (%d) y el id del path (%d) no coinciden",
                pedido.getId(), idPedido)
            );
        }

        // 2. Verificar existencia del pedido
        if (!pedidoRepository.existsById(idPedido)) {
            throw new PedidoNotFoundException("El pedido con id " + idPedido + " no existe.");
        }

        // 3. Recuperar pedido de la BD
        Pedido pedidoBD = pedidoRepository.findById(idPedido).get();

        // 4. Actualizar campos
        pedidoBD.setIdCliente(pedido.getIdCliente());
        pedidoBD.setFecha(pedido.getFecha());
        pedidoBD.setTotal(pedido.getTotal());
        pedidoBD.setMetodo(pedido.getMetodo());
        pedidoBD.setNotas(pedido.getNotas());

        // 5. Guardar cambios
        return pedidoRepository.save(pedidoBD);
    }

    // delete
    public void delete(int idPedido) {
        if (!this.pedidoRepository.existsById(idPedido)) {
            throw new PedidoNotFoundException("El pedido con id " + idPedido + " no existe.");
        }
        this.pedidoRepository.deleteById(idPedido);
    }
    
 // Añadir o modificar notas del pedido
    public Pedido actualizarNotas(int idPedido, String notas) {
        if (!pedidoRepository.existsById(idPedido)) {
            throw new PedidoNotFoundException("El pedido con id " + idPedido + " no existe.");
        }

        Pedido pedido = pedidoRepository.findById(idPedido).get();
        pedido.setNotas(notas);
        return pedidoRepository.save(pedido);
    }
	
}