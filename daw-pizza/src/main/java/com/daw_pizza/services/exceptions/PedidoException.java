package com.daw_pizza.services.exceptions;

public class PedidoException extends RuntimeException {

	private static final long serialVersionUID = 8954845870461706054L;
	
	public PedidoException(String message) {
		super(message);
	}
}