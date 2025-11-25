package com.daw_pizza.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw_pizza.persistence.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}