package com.junit.junit5Application.exception;

public class InsufficientAccountBalanceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InsufficientAccountBalanceException(String exceptionDescription) {
		super(exceptionDescription);
	}
}