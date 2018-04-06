package main.entitys;

import main.XY;

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
