package com.junit.junit5Application.exception;

public class InvalidAccountException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidAccountException(String exceptionDescription) {
		super(exceptionDescription);
	}
}