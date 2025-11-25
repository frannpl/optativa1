package com.daw_pizza.services.exceptions;

public class PedidoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1040591088342996302L;

	public PedidoNotFoundException(String message) {
		super(message);
}
}