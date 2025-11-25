package com.daw_pizza.services.exceptions;

public class PizzaPedidoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1040591088342996302L;

	public PizzaPedidoNotFoundException(String message) {
		super(message);
}

}