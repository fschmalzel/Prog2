package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.core.ui.console.ConsoleUI;
import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;

public class GameImpl extends Game {
	
	private HandOperatedMasterSquirrel masterSquirrel;
	
	public GameImpl() {
		
		super(new State(),new ConsoleUI());	
		masterSquirrel = new HandOperatedMasterSquirrel(
				XY.getRandomCoordinates(state.getBoard().getConfig().getSize(), state.getBoard().getEntitys()));
		state.insertMaster(masterSquirrel);
	}
	
	@Override
	protected void processInput() {
		masterSquirrel.setCommand(ui.getCommand());
	}

	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
	}

}
