package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.BadBeast;
import de.hsa.g17.fatsquirrel.entities.BadPlant;
import de.hsa.g17.fatsquirrel.entities.GoodBeast;
import de.hsa.g17.fatsquirrel.entities.GoodPlant;
import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;
import de.hsa.g17.fatsquirrel.entities.MiniSquirrel;
import de.hsa.g17.fatsquirrel.entities.Wall;

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
		this.energy += energy;
	}
	
	public XY getXY() {
		return xy;
	}
	
	public void move(XY vector) {
		xy = xy.add(vector);
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
			return EntityType.UNDEFINED;
	}
	
}
