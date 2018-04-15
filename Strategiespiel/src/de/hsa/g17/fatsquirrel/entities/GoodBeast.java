package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Board;
import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.XY;

public class GoodBeast extends Entity {
	
	private static final int DEFAULT_ENERGY = 200;
	
	public GoodBeast(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public GoodBeast(Board board) {
		super(DEFAULT_ENERGY, board);
	}
	
	@Override
	public void nextStep() {
		this.move(XY.getRandomVector());
	}
	
	public String toString() {
		return "GoodBeast" + super.toString();
	}
}
