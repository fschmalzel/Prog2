package de.hsa.g17.fatsquirrel.util.ui.console.sample;

import de.hsa.g17.fatsquirrel.util.ui.console.CommandTypeInfo;

public enum MyFavoriteCommandType implements CommandTypeInfo{
	HELP("help", "  * list all commands"),
	EXIT("exit", "  * exit program"),
	ADDI("addi", "<param1>  <param2>   * simple integer add ", Integer.class, Integer.class ),
	ADDF("addf", "<param1>  <param2>   * simple float add ",Float.class, Float.class ),
	ECHO("echo", "<param1>  <param2>   * echos param1 string param2 times ",String.class, Integer.class );
	
	private String name;
	private String helpText;
	private Class<?>[] params;
	
	private MyFavoriteCommandType(String name, String helpText, Class<?>[] params) {
		this.name = name;
		this.helpText = helpText;
		this.params = params;
	}
	
	private MyFavoriteCommandType(String name, String helpText) {
		this(name, helpText, new Class<?>[0]);
	}
	
	private MyFavoriteCommandType(String name, String helpText, Class<?> param1Type, Class<?> param2Type) {
		this(name, helpText, new Class<?>[] {param1Type, param2Type});
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getHelpText() {
		return helpText;
	}

	@Override
	public Class<?>[] getParamTypes() {
		return params;
	}
}
