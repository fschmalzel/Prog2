package de.hsa.g17.fatsquirrel.core.ui.console;

import de.hsa.g17.fatsquirrel.util.ui.console.AsCommand;

public interface GameCommands {
	
	@AsCommand(getName = "help", getHelpText = "  * list all commands")
	public void help();
	
	@AsCommand(getName = "exit", getHelpText = "  * exit game")
	public void exit();
	
	@AsCommand(getName = "left", getHelpText = "  * moves the master left")
	public void left();
	
	@AsCommand(getName = "right", getHelpText = "  * moves the master right")
	public void right();
	
	@AsCommand(getName = "up", getHelpText = "  * moves the master up")
	public void up();
	
	@AsCommand(getName = "down", getHelpText = "  * moves the master down")
	public void down();
	
	@AsCommand(getName = "all", getHelpText = "  * ???")
	public void all();
	
	@AsCommand(getName = "masterenergy", getHelpText = "  * shows the energy level of the master")
	public void masterenergy();
	
	@AsCommand(getName = "spawn_mini", getHelpText = "<energy> <left/right> <up/down>  * spawns a mini squirrel relative to the master squirrel."
			+ "Direction has to be either 1, 0 or -1.")
	public void spawnmini(Integer energy, Integer xOffset, Integer yOffset);
	
	
}
