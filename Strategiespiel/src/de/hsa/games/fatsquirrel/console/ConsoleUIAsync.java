package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.core.GameCommand;

public class ConsoleUIAsync extends ConsoleUI {
	
	private GameCommand cmd;
	
	public ConsoleUIAsync() {
		super();
	}
	
	public void process() {
		while(true)
			cmd = super.getCommand();
	}
	
	@Override
	public GameCommand getCommand() {
		GameCommand tmp = cmd;
		cmd = null;
		return tmp;
	}
	
}
