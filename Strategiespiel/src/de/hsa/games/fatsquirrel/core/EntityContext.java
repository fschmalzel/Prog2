package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Squirrel;
import de.hsa.games.fatsquirrel.util.XY;

public interface EntityContext {
	
	/**
	 * @return The size of the {@link Board}.
	 */
	XY getSize();
	
	/**
	 * @param miniSquirrel The {@link MiniSquirrel} that wants to move.
	 * @param moveDirection The direction that the {@link MiniSquirrel} wants to go in.
	 */
	void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
	
	/**
	 * @param goodBeast The {@link GoodBeast} that wants to move.
	 * @param moveDirection The direction that the {@link GoodBeast} wants to go in.
	 */
	void tryMove(GoodBeast goodBeast, XY moveDirection);
	
	/*
	 * @param badBeast The {@link BadBeast} that wants to move.
	 * @param moveDirection The direction that the {@link BadBeast} wants to go in.
	 */
	void tryMove(BadBeast badBeast, XY moveDirection);
	
	/**
	 * @param masterSquirrel The {@link MasterSquirrel} that wants to move.
	 * @param moveDirection The direction that the {@link MasterSquirrel} wants to go in.
	 */
	void tryMove(MasterSquirrel masterSquirrel, XY moveDirection);
	
	/**
	 * @param pos Center position.
	 * @return The {@link Squirrel} that is the nearest to the specified position.
	 */
	Squirrel nearestSquirrel(XY pos);
	
	/**
	 * @param entity The {@link Entity} that should be removed.
	 */
	void kill(Entity entity);
	
	/**
	 * @param entity The {@link Entity} that should be removed and replaced with a new {@link Entity} of the same type.
	 */	
	void killAndReplace(Entity entity);
	
	/**
	 * @param xy The position to be checked
	 * @return Returns the {@link EntityType} of the {@link Entity} at the specified position.
	 */
	EntityType getEntityType(XY xy);
	
	/**
	 * @param entity The entity which should be inserted.
	 * @return If the insertion was successful.
	 */
	boolean tryInsert(Entity entity);
	
	/**
	 * @param xy The position.
	 * @return The {@link Entity} at the specified position.
	 */
	Entity getEntity(XY xy);
	
	/**
	 * @param miniSquirrel which shall implode.
	 * @param impactRadius the radius in that the {@link MiniSquirrel} wants to implode in.
	 */
	void implode(MiniSquirrel miniSquirrel, int impactRadius);
	
	/**
	 * @return The remaining steps.
	 */
	long getRemainingSteps();
	
}
