package ru.ifmo.mpi.magichospital.healer.exception;

public class PossibleSqlInjectionAttackException extends Exception {

	private static final long serialVersionUID = -8294186439649065505L;

	public PossibleSqlInjectionAttackException(String message) {
		super(message);
	}
	
}
