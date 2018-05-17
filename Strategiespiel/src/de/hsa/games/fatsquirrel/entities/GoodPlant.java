package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.util.XY;

public class GoodPlant extends Entity {
	
	private static final int DEFAULT_ENERGY = 100;
	
	public GoodPlant(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}

	public String toString() {
		return "GoodPlant" + super.toString();
	}
}
