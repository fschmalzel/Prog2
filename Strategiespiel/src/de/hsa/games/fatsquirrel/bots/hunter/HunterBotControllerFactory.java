package de.hsa.games.fatsquirrel.bots.hunter;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.core.EntityType;

public class HunterBotControllerFactory implements BotControllerFactory {

	@Override
	public BotController createMasterBotController() {
		return new HunterBotController(EntityType.MASTER_SQUIRREL);
	}

	@Override
	public BotController createMiniBotController() {
		return new HunterBotController(EntityType.MINI_SQUIRREL);
	}

}
