package br.com.error.exception;

public class EmptyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public EmptyException(String msg) {
		super(msg);
	}

}
