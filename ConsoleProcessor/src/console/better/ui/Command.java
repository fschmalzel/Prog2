package console.better.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class Command implements Executable {
	
	CommandType cmdType;
	Object[] params;

	public Command(CommandType cmdType, Object[] params) {
		this.cmdType = cmdType;
		this.params = params;
	}

	@Override
	public Object execute() {
		try {
			return cmdType.getMethod().invoke(cmdType.getTarget(), params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Logger.getLogger(Command.class.getName()).warning("Error executing command \"" + cmdType.getName() + "\": " + e.toString());
		}
		return null;
	}

}
