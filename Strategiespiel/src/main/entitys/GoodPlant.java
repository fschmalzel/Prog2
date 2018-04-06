package main.entitys;

import main.XY;

public class GoodPlant extends Entity {
	
	private static final int DEFAULT_ENERGY = 100;
	
	public GoodPlant(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public GoodPlant(int x, int y) {
		super(DEFAULT_ENERGY, x, y);
	}

	@Override
	public void nextStep() {}

	public String toString() {
		return "GoodPlant" + super.toString();
	}
}
