package de.hasa.g17.fatsquirrel.util.ui.consoletest;

public enum MyFavoriteCommandType implements CommandTypeInfo{
	HELP, EXIT, ADDI, ADDF, ECHO;

	@Override
	public String getName() {
		 //TODO: ist das überhaupt richtig ? 
		if(this == HELP)
			return "help";
		else if(this == EXIT)
			return "exit";
		else if(this == ADDI)
			return "addi";
		else if(this == ADDF)
			return "addf";
		else if(this == ECHO)
			return "echo";
		return "undefined";
	}

	@Override
	public String getHelpText() {
		if(this == HELP)
			return "* list all commands";
		else if(this == EXIT)
			return "* exit program";
		else if(this == ADDI)
			return "<param1>  <param2>   * simple integer add ";
		else if(this == ADDF)
			return "<param1>  <param2>   * simple float add ";
		else if(this == ECHO)
			return "<param1>  <param2>   * echos param1 string param2 times ";
		return "undefined";
	}

	@Override
	public Class<?>[] getParamTypes() {
//		if(this == HELP)
//			return null;
//		else if(this == EXIT)
//			return null;
//		else if(this == ADDI)
//			return int.class;
//		else if(this == ADDF)
//			return float.class;
//		else if(this == ECHO)
//			return String.class;
		return null;
	}
}
