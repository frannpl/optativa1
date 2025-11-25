package com.daw_pizza.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw_pizza.persistence.entities.PizzaPedido;

public interface PizzaPedidoRepository extends JpaRepository<PizzaPedido, Integer> {

	List<PizzaPedido> findByIdPedido(int idPedido);
}