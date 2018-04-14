package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.XY;

public class Wall extends Entity{
	
	private static final int DEFAULT_ENERGY = -10;
	
	public Wall(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public Wall(int x, int y) {
		super(DEFAULT_ENERGY, x, y);
	}

	@Override
	public void nextStep() {}

	public String toString() {
		return "Wall" + super.toString();
	}
}