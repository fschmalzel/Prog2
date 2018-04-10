package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.XY;

public class BadPlant extends Entity {
	
	private static final int DEFAULT_ENERGY = -100;
	
	public BadPlant(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public BadPlant(int x, int y) {
		super(DEFAULT_ENERGY, x, y);
	}

	@Override
	public void nextStep() {}

	public String toString() {
		return "BadPlant" + super.toString();
	}
}
