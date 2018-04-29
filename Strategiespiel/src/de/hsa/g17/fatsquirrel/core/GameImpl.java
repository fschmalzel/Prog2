package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.core.ui.console.ConsoleUI;
import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;

public class GameImpl extends Game {

	private UI ui;
	private MasterSquirrel masterSquirrel;
	
	public GameImpl() {
		
		super(new State(),new ConsoleUI());	
		//TODO wir hatten hier ein ui erstellt aber warum?
		masterSquirrel = new HandOperatedMasterSquirrel(
				XY.getRandomCoordinates(state.getBoard().getConfig().getSize(), state.getBoard().getEntitys()));
		state.insertMaster(masterSquirrel);
	}
	
	@Override
	protected void processInput() {
		MoveCommand cmd;
		
		do {
			cmd = ui.getCommand();
		} while (cmd == null);
		
		masterSquirrel.setMoveCommand(cmd);
		
	}

	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
	}

}
