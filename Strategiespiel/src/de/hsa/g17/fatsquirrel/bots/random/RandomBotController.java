package de.hsa.g17.fatsquirrel.bots.random;

import de.hsa.g17.fatsquirrel.botapi.BotController;
import de.hsa.g17.fatsquirrel.botapi.ControllerContext;
import de.hsa.g17.fatsquirrel.core.XY;

public class RandomBotController implements BotController {

	@Override
	public void nextStep(ControllerContext view) {
		view.move(XY.getRandomVector());
	}

}
