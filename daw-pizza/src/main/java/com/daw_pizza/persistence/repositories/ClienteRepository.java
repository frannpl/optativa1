package com.daw_pizza.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw_pizza.persistence.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	Cliente findByTelefono(String telefono);
}