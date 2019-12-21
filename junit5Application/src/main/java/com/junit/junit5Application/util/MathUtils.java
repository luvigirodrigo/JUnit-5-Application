package com.junit.junit5Application.util;

public class MathUtils {
	public int add(int firstNumber, int secondNumber) {
		return (firstNumber + secondNumber);
	}

	public int substract(int firstNumber, int secondNumber) {
		return (firstNumber - secondNumber);
	}

	public int multiply(int firstNumber, int secondNumber) {
		return (firstNumber * secondNumber);
	}

	public int divide(int firstNumber, int secondNumber) {
		return (firstNumber / secondNumber);
	}

	// test driven development - stub a method (create a dummy method)
	// Write the test first and then write the method
	public double computeCircleArea(double radius) {
		return Math.PI * radius * radius;
	}
}