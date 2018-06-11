package de.hsa.games.fatsquirrel.console;

import java.lang.reflect.InvocationTargetException;

/**
 * A command that has been read from the console and that is executabl.
 */
public class Command implements Executable {
	
	CommandType cmdType;
	Object[] params;

	public Command(CommandType cmdType, Object[] params) {
		this.cmdType = cmdType;
		this.params = params;
	}
	
	/**
	 * Will invoke the underlying method with the parameters of the console and return the result.
	 * 
	 * @return An {@link Object} which represents the return value of the invoked method.
	 */
	@Override
	public Object execute() {
		try {
			return cmdType.getMethod().invoke(cmdType.getTarget(), params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("Error executing command!");
		}
	}

}
