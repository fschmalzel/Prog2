package main.entitys;

import main.XY;

public class GoodBeast extends Entity {
	
	private static final int DEFAULT_ENERGY = 200;
	
	public GoodBeast(int id, XY xy) {
		super(id, DEFAULT_ENERGY, xy);
	}
	
	public GoodBeast(int id, int x, int y) {
		super(id, DEFAULT_ENERGY, x, y);
	}

	@Override
	public void nextStep() {
		this.move(XY.getRandomVector());
	}
	
	public String toString() {
		return "GoodBeast" + super.toString();
	}
}
