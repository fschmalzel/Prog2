package de.hsa.g17.fatsquirrel.botapi;

public interface BotControllerFactory {
	BotController createMasterBotController();
	BotController createMiniBotController();

}
