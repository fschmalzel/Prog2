package de.hsa.games.fatsquirrel.console;

/**
 * Is thrown, when the user inputs a command that isn't specified.
 */
public class NoSuchCommandException extends ScanException {
	
	public NoSuchCommandException(String name) {
		super(name);
	}

	private static final long serialVersionUID = -462086939709055803L;
}
