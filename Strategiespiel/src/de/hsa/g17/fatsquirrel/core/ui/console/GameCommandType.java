package de.hsa.g17.fatsquirrel.core.ui.console;

import de.hsa.g17.fatsquirrel.util.ui.console.CommandTypeInfo;

public enum GameCommandType implements CommandTypeInfo{
	HELP("help", "  * list all commands"),
	EXIT("exit", "  * exit program"),
	LEFT("left", "  * moves the master left"),
	RIGHT("right", "  * moves the master right"),
	UP("up", "  * moves the master up"),
	DOWN("down", "  * moves the master down"),
	ALL("all", "  * ???"),
	MASTER_ENERGY("masterenergy", "  * shows the engery level of the master"),
	SPAWN_MINI("spawnmini", "<energy> <left/right> <up/down>  * spawns a mini squirrel relative to the master squirrel."
			+ "Direction has to be either 1, 0 or -1.", new Class<?>[] {Integer.class, Integer.class, Integer.class});

	private String name;
	private String helpText;
	private Class<?>[] params;

	private GameCommandType(String name, String helpText, Class<?>[] params) {
		this.name = name;
		this.helpText = helpText;
		this.params = params;
	}
	
	private GameCommandType(String name, String helpText) {
		this(name, helpText, new Class<?>[0]);
	}
	
	@Override
	public String getHelpText() {
		return helpText;
	}

	@Override
	public Class<?>[] getParamTypes() {
		return params;
	}

	@Override
	public String getName() {
		return name;
	}

}
