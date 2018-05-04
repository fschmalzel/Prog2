package console.ui;

public interface CommandTypeInfo {
	
	public String getHelpText();
	public Class<?>[] getParamTypes();
	public String getName();
	
}
