package de.hsa.games.fatsquirrel.bot.random;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class RandomBotController implements BotController {

	@Override
	public void nextStep(ControllerContext view) {
		view.move(XYsupport.getRandomVector());
	}

}
