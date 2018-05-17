package de.hsa.games.fatsquirrel.console;

public class ScanException extends RuntimeException {
	public ScanException(String name) {
		super(name);
	}
	
	public ScanException() {
		super();
	}

	private static final long serialVersionUID = 1662034559709965597L;
}