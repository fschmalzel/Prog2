package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Squirrel;
import de.hsa.games.fatsquirrel.util.XY;

public interface EntityContext {
	
	XY getSize();
	void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
	void tryMove(GoodBeast goodBeast, XY moveDirection);
	void tryMove(BadBeast badBeast, XY moveDirection);
	void tryMove(MasterSquirrel masterSquirrel, XY moveDirection);
	Squirrel nearestSquirrel(XY pos);
	
	void kill(Entity entity);
	void killAndReplace(Entity entity);
	EntityType getEntityType(XY xy);
	boolean tryInsert(Entity s);
	Entity getEntity(XY xy);
	void implode(MiniSquirrel m, int impactRadius);
	long getRemainingSteps();
	
}
