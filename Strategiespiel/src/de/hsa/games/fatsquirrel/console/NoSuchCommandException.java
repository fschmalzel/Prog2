package de.hsa.games.fatsquirrel.console;

public class NoSuchCommandException extends ScanException {
	
	public NoSuchCommandException(String name) {
		super(name);
	}

	private static final long serialVersionUID = -462086939709055803L;
}