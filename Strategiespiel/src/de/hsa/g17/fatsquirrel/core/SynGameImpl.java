package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;

public class SynGameImpl extends Game{

	HandOperatedMasterSquirrel masterSquirrel;
	
	SynGameImpl(BoardConfig boardConfig, UI ui) {
		super(boardConfig, ui);
		masterSquirrel = new HandOperatedMasterSquirrel(
				XY.getRandomCoordinates(boardConfig.getSize(), state.getBoard().getEntitys()));
		state.insertMaster(masterSquirrel);
	}
	
	public SynGameImpl(UI ui, BoardConfig boardConfig, BotControllerFactory factory) {
		this(boardConfig, ui);
		XY randCoords = XY.getRandomCoordinates(boardConfig.getSize(), state.getBoard().getEntitys());
		MasterSquirrelBot mBot = new MasterSquirrelBot(randCoords, factory.createMasterBotController(), factory);
		state.getBoard().insert(mBot);
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
		}
	}

	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
	}
}
