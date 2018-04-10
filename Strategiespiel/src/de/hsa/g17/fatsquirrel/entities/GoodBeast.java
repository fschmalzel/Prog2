package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.XY;

public class GoodBeast extends Entity {
	
	private static final int DEFAULT_ENERGY = 200;
	
	public GoodBeast(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public GoodBeast(int x, int y) {
		super(DEFAULT_ENERGY, x, y);
	}

	@Override
	public void nextStep() {
		this.move(XY.getRandomVector());
	}
	
	public String toString() {
		return "GoodBeast" + super.toString();
	}
}
