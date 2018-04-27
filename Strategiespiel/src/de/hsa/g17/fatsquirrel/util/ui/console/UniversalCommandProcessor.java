package de.hsa.g17.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Method;

public class UniversalCommandProcessor {
	private PrintStream outputStream = System.out;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	private CommandType[] commandTypes;
	
	
	public UniversalCommandProcessor(Class<?> interf, Object target) {
		
		Method[] methods = interf.getDeclaredMethods();
		int count = 0;
		CommandType[] commandTypes = new CommandType[methods.length];
		
		for (Method method : methods) {
			AsCommand ac = method.getAnnotation(AsCommand.class);
			if (ac == null)
				continue;
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
	
	public void process() {
		
		CommandScanner scanner = new CommandScanner(commandTypes, inputReader, outputStream);
		
		while (true) {
			Command cmd;
			try {
				cmd = scanner.next();
			} catch (NoSuchCommandException e) {
				
				continue;
			}
			
			new ExecutableCommand(cmd).execute();
			
			
		}
		
		
	}
	
	public static void main(String[] args) {
		UniversalCommandProcessor uniProcessor = new UniversalCommandProcessor(UserActions.class, new UserActionsImpl());
		uniProcessor.process();
		
		
	}
	
}
