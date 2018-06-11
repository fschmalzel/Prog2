package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.core.GameCommand;

/**
 * A {@link UI} that prints everything on the console and doesn't use any graphical interface / api.
 * Is using asynchronous input.
 */
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
