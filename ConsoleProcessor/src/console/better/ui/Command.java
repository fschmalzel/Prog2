package console.better.ui;

import java.lang.reflect.InvocationTargetException;

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
			// TODO Exception handling
		}
		return null;
	}

}
