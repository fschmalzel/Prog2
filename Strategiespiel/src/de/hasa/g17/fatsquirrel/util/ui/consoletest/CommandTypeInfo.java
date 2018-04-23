package de.hasa.g17.fatsquirrel.util.ui.consoletest;

public interface CommandTypeInfo {
	
	public String getHelpText();
	public Class<?>[] getParamTypes();
	public String getName();
}
