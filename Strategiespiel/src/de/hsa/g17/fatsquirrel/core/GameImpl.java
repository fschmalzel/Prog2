package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.g17.fatsquirrel.bots.random.RandomBotControllerFactory;
import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;

public class GameImpl extends Game {
	
	private HandOperatedMasterSquirrel masterSquirrel;
	
	public GameImpl(UI ui, BoardConfig boardConfig, boolean synchron) {
		
		super(boardConfig, ui, false);	
		masterSquirrel = new HandOperatedMasterSquirrel(
				XY.getRandomCoordinates(boardConfig.getSize(), state.getBoard().getEntitys()), ui);
		state.insertMaster(masterSquirrel);
		
		BotControllerFactory factory = new RandomBotControllerFactory();
		XY randCoords = XY.getRandomCoordinates(boardConfig.getSize(), state.getBoard().getEntitys());
		MasterSquirrelBot mBot = new MasterSquirrelBot(randCoords, factory.createMasterBotController(), factory);
		state.getBoard().insert(mBot);
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
