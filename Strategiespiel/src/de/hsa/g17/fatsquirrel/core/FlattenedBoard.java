package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.BadBeast;
import de.hsa.g17.fatsquirrel.entities.GoodBeast;
import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;
import de.hsa.g17.fatsquirrel.entities.MiniSquirrel;
import de.hsa.g17.fatsquirrel.entities.Squirrel;

public class FlattenedBoard implements BoardView, EntityContext {

	@Override
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
		// TODO tryMove

	}

	@Override
	public void tryMove(GoodBeast goodBeast, XY moveDirection) {
		// TODO tryMove

	}

	@Override
	public void tryMove(BadBeast badBeast, XY moveDirection) {
		// TODO tryMove

	}

	@Override
	public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
		// TODO tryMove

	}

	@Override
	public Squirrel nearestSquirrel(XY pos) {
		// TODO nearestSquirrel
		return null;
	}

	@Override
	public void kill(Entity entity) {
		// TODO kill

	}

	@Override
	public void killAndReplace(Entity entity) {
		// TODO killAndReplace

	}

	@Override
	public EntityType getEntityType(XY xy) {
		// TODO getEntityType
		return null;
	}

	@Override
	public XY getSize() {
		// TODO getSize
		return null;
	}

}
