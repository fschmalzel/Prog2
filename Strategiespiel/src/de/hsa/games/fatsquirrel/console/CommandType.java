package de.hsa.games.fatsquirrel.console;

import java.lang.reflect.Method;

/**
 * Specifies types of command, the method which should be called and the object on which it should be invoked.
 */
public class CommandType {
	
	private String helpText;
	private String name;
	private Object target;
	private Method method;
	
	public CommandType(String name, String helpText, Object target, Method method) {
		this.helpText = helpText;
		this.name = name;
		this.target = target;
		this.method = method;
	}

	/**
	 * @return A {@link String} that describes the command.
	 */
	public String getHelpText() {
		return helpText;
	}

	/**
	 * @return The types of the parameters, that this command accepts.
	 */
	public Class<?>[] getParamTypes() {
		return method.getParameterTypes();
	}

	/**
	 * @return The name of the command.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The underlying method that should be invoked up on execution.
	 */
	public Method getMethod() {
		return method;
	}
	
	/**
	 * @return The object on which the method should be called.
	 */
	public Object getTarget() {
		return target;
	}

}
