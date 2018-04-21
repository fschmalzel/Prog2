package de.hasa.g17.fatsquirrel.util.ui.consoletest;

public interface CommandTypeInfo {
	
	public String getHelpText();
	public Class<?>[] getParamTypes(); //TODO: Ka wie das mit den wildcard geht FELLLIXXXXX HEEEELLLPPP 
	public String getName();
}
