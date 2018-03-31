package main.entitys;

import main.XY;

public class GoodPlant extends Entity {
	
	private static final int DEFAULT_ENERGY = 100;
	
	public GoodPlant(int id, XY xy) {
		super(id, DEFAULT_ENERGY, xy);
	}
	
	public GoodPlant(int id, int x, int y) {
		super(id, DEFAULT_ENERGY, x, y);
	}

	@Override
	public void nextStep() {}

	public String toString() {
		return "GoodPlant" + super.toString();
	}
}
