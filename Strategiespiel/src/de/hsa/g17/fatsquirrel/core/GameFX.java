package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.core.ui.console.ConsoleUI;
import de.hsa.g17.fatsquirrel.core.ui.console.FxUI;
import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;

public class GameFX extends Game {
	
	private HandOperatedMasterSquirrel masterSquirrel;
	
	public GameFX() {
		
		super(new State(),FxUI.createInstance(state.getBoard().getConfig().getSize()), false);	
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
