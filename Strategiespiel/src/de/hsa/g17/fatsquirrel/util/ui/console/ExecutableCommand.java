package de.hsa.g17.fatsquirrel.util.ui.console;

import java.lang.reflect.InvocationTargetException;

public class ExecutableCommand extends Command implements Executable {
	
	CommandType cmdType;
	
	public ExecutableCommand(Command cmd) {
		super(cmd.getCommandType(), cmd.getParams());
		if (cmd.getCommandType() instanceof CommandType)
			cmdType = (CommandType) cmd.getCommandType();
		else 
			throw new InstantiationError();
	}

	@Override
	public Object execute() {
		try {
			return cmdType.getMethod().invoke(cmdType.getTarget(), getParams());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Exception handling
		}
		return null;
	}

}
