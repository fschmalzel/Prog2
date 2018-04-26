package de.hsa.g17.fatsquirrel.util.ui.console;

public interface CommandTypeInfo {
	
	public String getHelpText();
	public Class<?>[] getParamTypes();
	public String getName();
	public CommandTypeInfo[] values();
	
}
