package de.hsa.g17.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

public class CommandScanner {

	CommandType[] commandTypes;
	BufferedReader inputReader;
	OutputStream outputStream;
	
	public CommandScanner(CommandType[] commandTypes, BufferedReader inputReader, OutputStream outputStream) {
		this.inputReader = inputReader;
		this.outputStream = outputStream;
		this.commandTypes = commandTypes;
	}

	public Command next() {
		
		String input;
		try {
			input = inputReader.readLine();
		} catch (IOException e) {
			throw new ScanException();
		}

		int split = input.indexOf(' ');

		String name;
		if (split != -1) {
			name = input.substring(0, split);
			input = input.substring(split + 1);
		} else {
			name = input;
			input = "";
		}

		CommandType cmd = null;

		for (CommandType commandType : commandTypes) {
			if (commandType.getName().equalsIgnoreCase(name)) {
				cmd = commandType;
				break;
			}
		}
		
		if (cmd == null)
			throw new NoSuchCommandException();

		Object[] params = new Object[cmd.getParamTypes().length];
		
		for (int i = 0; i < cmd.getParamTypes().length; i++) {

			split = input.indexOf(' ');
			String param;
			if (split != -1) {
				param = input.substring(0, split);
				input = input.substring(split + 1);
			} else {
				param = input;
				input = "";
			}
			
			Class<?> paramType = cmd.getParamTypes()[i];
			try {
				params[i] = paramType.getConstructor(String.class).newInstance(param);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				throw new ScanException();
			}

		}
		
		return new Command(cmd, params);
	}

}
