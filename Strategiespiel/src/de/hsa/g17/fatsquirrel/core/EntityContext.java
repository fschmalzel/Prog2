package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.BadBeast;
import de.hsa.g17.fatsquirrel.entities.GoodBeast;
import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;
import de.hsa.g17.fatsquirrel.entities.MiniSquirrel;
import de.hsa.g17.fatsquirrel.entities.Squirrel;

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
	MoveCommand getCommand();
	
}
