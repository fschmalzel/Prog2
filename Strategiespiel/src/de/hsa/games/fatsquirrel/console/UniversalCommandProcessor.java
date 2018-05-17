package de.hsa.games.fatsquirrel.console;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.lang.reflect.Method;

public class UniversalCommandProcessor {
	private OutputStream outputStream;
	private BufferedReader inputReader;
	private CommandType[] commandTypes;
	
	public UniversalCommandProcessor(Class<?> interf, Object target, BufferedReader inputReader, OutputStream outputStream) {
		this.inputReader = inputReader;
		this.outputStream = outputStream;
		
		Method[] methods = interf.getDeclaredMethods();
		int count = 0;
		CommandType[] commandTypes = new CommandType[methods.length];
		
		for (Method method : methods) {
			AsCommand ac = method.getAnnotation(AsCommand.class);
			if (ac != null)
				commandTypes[count++] = new CommandType(ac.getName(), ac.getHelpText(), target, method);
		}
		
		if (count < commandTypes.length) {
			CommandType[] tmp = new CommandType[count];
			for (int i = 0; i < count; i++) {
				tmp[i] = commandTypes[i];
			}
			commandTypes = tmp;
		}
		
		this.commandTypes = commandTypes;
		
		
	}
	
	public CommandScanner getScanner() {
		return new CommandScanner(commandTypes, inputReader, outputStream);
	}
	
	public void process() {
		
		CommandScanner scanner = new CommandScanner(commandTypes, inputReader, outputStream);
		
		while (true) {
			Command cmd;
			try {
				cmd = scanner.next();
			} catch (NoSuchCommandException e) {
				
				continue;
			}
			
			@SuppressWarnings("unused")
			Object result = cmd.execute();
			
		}
		
	}
	
}
