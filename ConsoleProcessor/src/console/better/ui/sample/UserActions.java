package console.better.ui.sample;

import console.better.ui.AsCommand;

public interface UserActions {
	@AsCommand(getHelpText = "  * list all commands", getName = "help")
	public void help();
	
	@AsCommand(getHelpText = "  * exit program", getName = "exit")
	public void exit();
	
	@AsCommand(getHelpText = "<param1>  <param2>   * simple integer add ", getName = "addi")
	public void addi(Integer i, Integer j);
	
	@AsCommand(getHelpText = "<param1>  <param2>   * simple float add ", getName = "addf")
	public void addf(Float i, Float j);
	
	@AsCommand(getHelpText = "<param1>  <param2>   * echos param1 string param2 times ", getName = "echo")
	public void echo(String s, Integer i);
}
