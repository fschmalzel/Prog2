package console.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

public class MyFavoriteCommandsProcessor2 {
	private PrintStream outputStream = System.out;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	public void process() {
		CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(), inputReader, outputStream);
	       
		while (true) { // the loop over all commands with one input line for every command
			Command command;
			try {
				command = commandScanner.next();
			} catch (NoSuchCommandException e){
				e.printStackTrace(outputStream);
				continue;
			}

			Object[] params = command.getParams();

		    MyFavoriteCommandType commandType = (MyFavoriteCommandType) command.getCommandType();
		    
			try {
				this.getClass().getMethod(commandType.getName(), commandType.getParamTypes()).invoke(this, params);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void help() {
		for(CommandTypeInfo cmd : MyFavoriteCommandType.values())
			outputStream.println(cmd.getName() + "\t" + cmd.getHelpText());
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void addi(Integer i, Integer j) {
		outputStream.println(i + j);
	}
	
	public void addf(Float i, Float j) {
		outputStream.println(i + j);
	}
	
	public void echo(String s, Integer times) {
		for (int i = 0; i < times; i++)
			outputStream.println(s);
	}
	
	public static void main(String[] args) {
		MyFavoriteCommandsProcessor2 commandProcessor = new MyFavoriteCommandsProcessor2();
		commandProcessor.process();
	}


}
