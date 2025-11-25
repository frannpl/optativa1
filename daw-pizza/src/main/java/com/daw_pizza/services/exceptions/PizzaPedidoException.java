package com.daw_pizza.services.exceptions;

public class PizzaPedidoException extends RuntimeException {

	private static final long serialVersionUID = 8954845870461706054L;
	
	public PizzaPedidoException(String message) {
		super(message);
	}
}
