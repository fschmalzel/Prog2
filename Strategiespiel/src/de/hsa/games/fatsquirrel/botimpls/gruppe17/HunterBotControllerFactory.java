package de.hsa.games.fatsquirrel.botimpls.gruppe17;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;

/**
 * A simple bot that just runs towards {@link GoodBeast} and {@link GoodPlant} without path finding.
 */
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
