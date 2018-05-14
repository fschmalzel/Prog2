package de.hsa.g17.fatsquirrel.bots.random;

import de.hsa.g17.fatsquirrel.botapi.BotController;
import de.hsa.g17.fatsquirrel.botapi.BotControllerFactory;

public class RandomBotControllerFactory implements BotControllerFactory {

	@Override
	public BotController createMasterBotController() {
		return new RandomBotController();
	}

	@Override
	public BotController createMiniBotController() {
		return new RandomBotController();
	}

}
