package com.daw_pizza.services.exceptions;

public class ClienteException extends RuntimeException {

	private static final long serialVersionUID = 8954845870461706054L;
	
	public ClienteException(String message) {
		super(message);
	}
}