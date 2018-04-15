package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Board;
import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.XY;

public class BadBeast extends Entity {
	
	private static final int DEFAULT_ENERGY = -150;
	
	public BadBeast(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public BadBeast(Board board) {
		super(DEFAULT_ENERGY, board);
	}

	public void nextStep() {
		this.move(XY.getRandomVector());
	}
	
	public String toString() {
		return "BadBeast" + super.toString();
	}
	
}
