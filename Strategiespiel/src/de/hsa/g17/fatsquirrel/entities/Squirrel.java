package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.XY;

public abstract class Squirrel extends Entity {

	public Squirrel(int energy, XY xy) {
		super(energy, xy);
	}
	public Squirrel(int energy, int x, int y) {
		super(energy, x, y);
	}
	
}
