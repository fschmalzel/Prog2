package de.hsa.g17.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import de.hsa.g17.fatsquirrel.util.ui.console.Command;
import de.hsa.g17.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.g17.fatsquirrel.util.ui.console.CommandTypeInfo;
import de.hsa.g17.fatsquirrel.util.ui.console.NoSuchCommandException;

public class CommandsProcessor {
	private PrintStream outputStream = System.out;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	private CommandTypeInfo commandTypeInfo;
	private Class<?> clazz;
	
	CommandsProcessor(Class<?> clazz, CommandTypeInfo commandTypeInfo) {
		this.clazz = clazz;
		this.commandTypeInfo = commandTypeInfo;
	}
	
	public void process() {
		CommandScanner commandScanner = new CommandScanner(commandTypeInfo.values(), inputReader, outputStream);
	       
		while (true) { // the loop over all commands with one input line for every command
			Command command;
			try {
				command = commandScanner.next();
			} catch (NoSuchCommandException e){
				e.printStackTrace(outputStream);
				continue;
			}

			Object[] params = command.getParams();

		    CommandTypeInfo commandType = command.getCommandType();
		    
		    try {
				clazz.getMethod(commandType.getName(), commandType.getParamTypes()).invoke(clazz, params);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		    
		    
		}
	}

}
