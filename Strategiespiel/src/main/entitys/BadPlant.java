package main.entitys;

import main.XY;

public class BadPlant extends Entity {
	
	private static final int DEFAULT_ENERGY = -100;
	
	public BadPlant(int id, XY xy) {
		super(id, DEFAULT_ENERGY, xy);
	}
	
	public BadPlant(int id, int x, int y) {
		super(id, DEFAULT_ENERGY, x, y);
	}

	@Override
	public void nextStep() {}

	public String toString() {
		return "BadPlant" + super.toString();
	}
}
