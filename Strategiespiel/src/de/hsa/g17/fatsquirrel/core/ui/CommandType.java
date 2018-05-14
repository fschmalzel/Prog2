package de.hsa.g17.fatsquirrel.core.ui;

import java.lang.reflect.Method;

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

	public String getHelpText() {
		return helpText;
	}

	public Class<?>[] getParamTypes() {
		return method.getParameterTypes();
	}

	public String getName() {
		return name;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public Object getTarget() {
		return target;
	}

}
