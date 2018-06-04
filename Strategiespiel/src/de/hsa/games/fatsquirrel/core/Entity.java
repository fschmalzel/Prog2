package de.hsa.games.fatsquirrel.core;

import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;

public abstract class Entity {
	private final int id;
	private int energy;
	private XY xy;
	private static int lastID = -1;
	
	protected Entity(int energy, XY xy) {
		this.id = ++lastID;
		this.energy = energy;
		this.xy = xy;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void updateEnergy(int energy) {
		Logger.getLogger(Entity.class.getName()).finer("Entity " + ((energy < 0) ? "lost" : "got") + " " + energy);
		this.energy += energy;
	}
	
	public XY getXY() {
		return xy;
	}
	
	public void move(XY vector) {
		Logger.getLogger(Entity.class.getName()).finest("Entity " + getID() + " moved from " + xy + " to " + xy.plus(vector));
		xy = xy.plus(vector);
	}
	
	public int getID() {
		return id;
	}
	
	public boolean equals(Entity e) {
		if (e != null)
			return id == e.getID();
		return false;
	}
	
	public boolean isSameType(Entity e) {
		
		if (e == null)
			return false;
		else
			return this.getEntityType() == e.getEntityType();
		
	}
	
	public String toString() {
		return " with id " + id + " at " + xy.toString() + " with energy " + energy;
	}

	public EntityType getEntityType() {
		if (this instanceof BadBeast)
			return EntityType.BAD_BEAST;
		else if (this instanceof GoodBeast)
			return EntityType.GOOD_BEAST;
		else if (this instanceof BadPlant)
			return EntityType.BAD_PLANT;
		else if (this instanceof GoodPlant)
			return EntityType.GOOD_PLANT;
		else if (this instanceof Wall)
			return EntityType.WALL;
		else if (this instanceof MiniSquirrel)
			return EntityType.MINI_SQUIRREL;
		else if (this instanceof MasterSquirrel)
			return EntityType.MASTER_SQUIRREL;
		else
			return null;
	}
	
}
