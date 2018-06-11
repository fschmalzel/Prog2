package de.hsa.games.fatsquirrel.console;

/**
 * Is thrown, when there is any issue whilst scanning the next line.
 */
public class ScanException extends RuntimeException {
	public ScanException(String name) {
		super(name);
	}
	
	public ScanException() {
		super();
	}

	private static final long serialVersionUID = 1662034559709965597L;
}