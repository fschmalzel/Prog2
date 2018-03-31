package main.entitys;

import main.XY;

public class Wall extends Entity{
	
	private static final int DEFAULT_ENERGY = -10;
	
	public Wall(int id, XY xy) {
		super(id, DEFAULT_ENERGY, xy);
	}

	@Override
	public void nextStep() {}

	public String toString() {
		return "Wall" + super.toString();
	}
}
