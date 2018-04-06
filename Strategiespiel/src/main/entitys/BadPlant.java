package main.entitys;

import main.XY;

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
