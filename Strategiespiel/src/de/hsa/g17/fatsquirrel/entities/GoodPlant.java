package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Board;
import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.EntityContext;
import de.hsa.g17.fatsquirrel.core.XY;

public class GoodPlant extends Entity {
	
	private static final int DEFAULT_ENERGY = 100;
	
	public GoodPlant(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public GoodPlant(Board board) {
		super(DEFAULT_ENERGY, board);
	}

	@Override
	public void nextStep(EntityContext context) {}

	public String toString() {
		return "GoodPlant" + super.toString();
	}
}
