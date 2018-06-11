package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.core.SpawnCommand;

/**
 * Specifies all valid commands for the console.
 */
public interface CommandTypes {
	
	@AsCommand(getName = "help", getHelpText = "  * list all commands")
	public void help();
	
	@AsCommand(getName = "exit", getHelpText = "  * exit game")
	public void exit();
	
	@AsCommand(getName = "left", getHelpText = "  * moves the master left")
	public MoveCommand left();
	
	@AsCommand(getName = "right", getHelpText = "  * moves the master right")
	public MoveCommand right();
	
	@AsCommand(getName = "up", getHelpText = "  * moves the master up")
	public MoveCommand up();
	
	@AsCommand(getName = "down", getHelpText = "  * moves the master down")
	public MoveCommand down();
	
	@AsCommand(getName = "all", getHelpText = "  * ???")
	public GameCommand all();
	
	@AsCommand(getName = "masterenergy", getHelpText = "  * shows the energy level of the master")
	public GameCommand masterenergy();
	
	@AsCommand(getName = "spawnmini", getHelpText = "<energy> <left/right> <up/down>  * spawns a mini squirrel relative to the master squirrel."
			+ "Direction has to be either 1, 0 or -1.")
	public SpawnCommand spawnmini(Integer energy, Integer xOffset, Integer yOffset);
	
	
	
}
