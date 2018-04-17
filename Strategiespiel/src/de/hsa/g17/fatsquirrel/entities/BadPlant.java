package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Board;
import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.XY;

public class BadPlant extends Entity {
	
	private static final int DEFAULT_ENERGY = -100;
	
	public BadPlant(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public BadPlant(Board board) {
		super(DEFAULT_ENERGY, board);
	}

	public String toString() {
		return "BadPlant" + super.toString();
	}
}
