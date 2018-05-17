package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.util.XY;

public class Wall extends Entity{
	
	private static final int DEFAULT_ENERGY = -10;
	
	public Wall(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}

	public String toString() {
		return "Wall" + super.toString();
	}
}
