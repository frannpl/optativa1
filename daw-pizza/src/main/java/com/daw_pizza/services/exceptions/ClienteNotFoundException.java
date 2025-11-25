package com.daw_pizza.services.exceptions;

public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1040591088342996302L;

	public ClienteNotFoundException(String message) {
		super(message);
}
}