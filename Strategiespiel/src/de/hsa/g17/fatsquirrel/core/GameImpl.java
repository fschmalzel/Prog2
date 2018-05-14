package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;

public class GameImpl extends Game {
	
	private HandOperatedMasterSquirrel masterSquirrel;
	
	public GameImpl(UI ui, BoardConfig boardConfig, boolean synchron) {
		
		super(boardConfig, ui, false);	
		masterSquirrel = new HandOperatedMasterSquirrel(
				XY.getRandomCoordinates(boardConfig.getSize(), state.getBoard().getEntitys()));
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
