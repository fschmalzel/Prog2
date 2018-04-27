package de.hsa.g17.fatsquirrel.util.ui.console;

import java.lang.reflect.Method;

public class CommandType implements CommandTypeInfo {
	
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

	@Override
	public String getHelpText() {
		return helpText;
	}

	@Override
	public Class<?>[] getParamTypes() {
		return method.getParameterTypes();
	}

	@Override
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
