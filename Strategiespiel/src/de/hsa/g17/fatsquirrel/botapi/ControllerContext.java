package de.hsa.g17.fatsquirrel.botapi;

import de.hsa.g17.fatsquirrel.core.EntityType;
import de.hsa.g17.fatsquirrel.core.XY;

public interface ControllerContext {
	
	 XY getViewLowerLeft();
	 XY getViewUpperRight();
	 EntityType getEntityAt(XY xy);
	 void move(XY direction);
	 void spawnMiniBot(XY direction, int energy);
	 int getEnergy();
	 

}
