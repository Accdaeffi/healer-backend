package ru.ifmo.mpi.magichospital.healer.exception;

public class NoEntityWithSuchIdException extends Exception {
	
	private static final long serialVersionUID = 5394947760304831837L;

	public NoEntityWithSuchIdException(String message) {
		super(message);
	}

}
