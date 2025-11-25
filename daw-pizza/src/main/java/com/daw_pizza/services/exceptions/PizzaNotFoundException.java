package com.daw_pizza.services.exceptions;

public class PizzaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1040591088342996302L;

	public PizzaNotFoundException(String message) {
		super(message);
}

}