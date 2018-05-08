package de.hsa.g17.fatsquirrel.core.ui.console;

public class ScanException extends RuntimeException {
	public ScanException(String name) {
		super(name);
	}
	
	public ScanException() {
		super();
	}

	private static final long serialVersionUID = 1662034559709965597L;
}