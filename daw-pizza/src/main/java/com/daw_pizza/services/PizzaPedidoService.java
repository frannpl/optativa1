package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Pedido;
import com.daw_pizza.persistence.entities.PizzaPedido;
import com.daw_pizza.persistence.repositories.PedidoRepository;
import com.daw_pizza.persistence.repositories.PizzaPedidoRepository;
import com.daw_pizza.services.exceptions.PizzaPedidoException;
import com.daw_pizza.services.exceptions.PizzaPedidoNotFoundException;
import com.daw_pizza.services.exceptions.PedidoNotFoundException;

@Service
public class PizzaPedidoService {

    @Autowired
    private PizzaPedidoRepository pizzaPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PizzaPedido> findAll() {
        return this.pizzaPedidoRepository.findAll();
    }

    // findById
    public PizzaPedido findById(int idPizzaPedido) {
        if (!this.pizzaPedidoRepository.existsById(idPizzaPedido)) {
            throw new PizzaPedidoNotFoundException(
                "El registro pizza_pedido con id " + idPizzaPedido + " no existe.");
        }
        return this.pizzaPedidoRepository.findById(idPizzaPedido).get();
    }

    // create
    public PizzaPedido create(PizzaPedido pizzaPedido) {
        pizzaPedido.setId(0); // Forzar nuevo registro
        PizzaPedido creado = this.pizzaPedidoRepository.save(pizzaPedido);
        recalcularTotalPedido(creado.getIdPedido());
        return creado;
    }

    // update
    public PizzaPedido update(PizzaPedido pizzaPedido, int idPizzaPedido) {

        // 1. Verificar que los IDs coinciden
        if (pizzaPedido.getId() != idPizzaPedido) {
            throw new PizzaPedidoException(
                String.format("El id del body (%d) y el id del path (%d) no coinciden",
                    pizzaPedido.getId(), idPizzaPedido));
        }

        // 2. Verificar existencia del registro
        if (!pizzaPedidoRepository.existsById(idPizzaPedido)) {
            throw new PizzaPedidoNotFoundException(
                "El registro pizza_pedido con id " + idPizzaPedido + " no existe.");
        }

        // 3. Recuperar registro desde BD
        PizzaPedido pizzaPedidoBD = pizzaPedidoRepository.findById(idPizzaPedido).get();

        // 4. Actualizar campos
        pizzaPedidoBD.setIdPizza(pizzaPedido.getIdPizza());
        pizzaPedidoBD.setIdPedido(pizzaPedido.getIdPedido());
        pizzaPedidoBD.setCantidad(pizzaPedido.getCantidad());
        pizzaPedidoBD.setPrecio(pizzaPedido.getPrecio());

        // 5. Guardar cambios
        PizzaPedido actualizado = pizzaPedidoRepository.save(pizzaPedidoBD);
        recalcularTotalPedido(actualizado.getIdPedido());
        return actualizado;
    }

    // delete
    public void delete(int idPizzaPedido) {
        if (!this.pizzaPedidoRepository.existsById(idPizzaPedido)) {
            throw new PizzaPedidoNotFoundException(
                "El registro pizza_pedido con id " + idPizzaPedido + " no existe.");
        }

        PizzaPedido existente = pizzaPedidoRepository.findById(idPizzaPedido).get();
        int idPedido = existente.getIdPedido();

        this.pizzaPedidoRepository.deleteById(idPizzaPedido);
        recalcularTotalPedido(idPedido);
    }

    // ---- lógica de negocio: recalcular total del pedido ----
    private void recalcularTotalPedido(int idPedido) {

        // 1. Recuperar pedido
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(
            () -> new PedidoNotFoundException("El pedido con id " + idPedido + " no existe."));

        // 2. Recuperar todas las pizzas del pedido
        List<PizzaPedido> lineas = pizzaPedidoRepository.findByIdPedido(idPedido);

        // 3. Calcular total = sum(cantidad * precioUnitario)
        double total = 0.0;
        for (PizzaPedido linea : lineas) {
            total += linea.getCantidad() * linea.getPrecio();
        }

        // 4. Guardar total en el pedido
        pedido.setTotal(total);
        pedidoRepository.save(pedido);
    }
}
