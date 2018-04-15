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
	
	protected Entity(int energy, Board board) {
		this(energy, board.randomCoordinates());
	}
	
	public abstract void nextStep();
	
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
		else if(this instanceof BadBeast && e instanceof BadBeast)
			return true;
		else if(this instanceof GoodBeast && e instanceof GoodBeast)
			return true;
		else if(this instanceof BadPlant && e instanceof BadPlant)
			return true;
		else if(this instanceof GoodPlant && e instanceof GoodPlant)
			return true;
		else if(this instanceof Wall && e instanceof Wall)
			return true;
		else if(this instanceof MasterSquirrel && e instanceof MasterSquirrel)
			return true;
		else if(this instanceof MiniSquirrel && e instanceof MiniSquirrel)
			return true;
		else
			return false;
		
	}
	
	public String toString() {
		return " with id " + id + " at " + xy.toString() + " with energy " + energy;
	}
	
}
