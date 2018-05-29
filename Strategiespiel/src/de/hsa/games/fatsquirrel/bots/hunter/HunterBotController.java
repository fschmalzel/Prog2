package de.hsa.games.fatsquirrel.bots.hunter;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class HunterBotController implements BotController {
	
	private EntityType type;
	
	public HunterBotController(EntityType type) {
		this.type = type;
	}
	
	@Override
	public void nextStep(ControllerContext context) {
		
		XY xy = null;
		
		for(int x = context.getViewLowerLeft().x; x <= context.getViewUpperRight().x; x++) {
			for(int y = context.getViewUpperRight().y; y <= context.getViewLowerLeft().y; y++) {
				XY temp = new XY(x, y);
				EntityType tempType = context.getEntityAt(new XY(x, y));
				if(tempType == EntityType.GOOD_BEAST || tempType == EntityType.GOOD_PLANT) {
					if (xy == null || context.locate().distanceFrom(xy) > context.locate().distanceFrom(temp)) {
						xy = temp;
					}
				}
			}
		}
		
		if (xy == null) {
			context.move(XYsupport.getRandomVector());
			return;
		}
		
		int x, y;
		int diff = xy.x - context.locate().x;
		if (diff > 0)
			x = 1;
		else if (diff < 0)
			x = -1;
		else 
			x = 0;
		
		diff = xy.y - context.locate().y;
		if (diff > 0)
			y = 1;
		else if (diff < 0)
			y = -1;
		else 
			y = 0;
		
		context.move(new XY(x, y));
		
	}

}
