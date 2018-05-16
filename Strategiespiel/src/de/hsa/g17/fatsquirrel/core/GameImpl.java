package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;

public class GameImpl extends Game {
	
	private HandOperatedMasterSquirrel masterSquirrel;
	
	public GameImpl(BoardConfig boardConfig, UI ui) {
		super(boardConfig, ui);
		
		//Temporary
		masterSquirrel = new HandOperatedMasterSquirrel(
				XY.getRandomCoordinates(boardConfig.getSize(), state.getBoard().getEntitys()));
		state.insertMaster(masterSquirrel);
		
	}
	
	@Override
	protected void processInput() {
		GameCommand cmd = ui.getCommand();
		
		if(cmd == null)
			return;
		
		switch(cmd.getType()) {
		case MASTERENERGY:
			ui.message("master energy: " + masterSquirrel.getEnergy());
			break;
		case ALL:
			ui.message(state.getBoard().toString());
			break;
		default:
			masterSquirrel.setCommand(cmd);
			return;
		}
	}

	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
	}

}
