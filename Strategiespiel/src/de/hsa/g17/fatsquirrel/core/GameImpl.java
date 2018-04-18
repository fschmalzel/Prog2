package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;

public class GameImpl extends Game {

	private UI ui;
	private MasterSquirrel masterSquirrel;
	
	public GameImpl() {
		super(new State());
		ui = new ConsoleUI();
		masterSquirrel = new HandOperatedMasterSquirrel(state.board.randomCoordinates(), ui);
		state.insertMaster(masterSquirrel);
	}
	
	@Override
	protected void processInput() {
		if(!masterSquirrel.isStunnedNextRound())
			masterSquirrel.setMoveCommand(ui.getCommand());
	}

	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
	}

}
