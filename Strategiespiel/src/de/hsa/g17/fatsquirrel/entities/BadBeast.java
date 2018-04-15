package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Board;
import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.EntityContext;
import de.hsa.g17.fatsquirrel.core.XY;

public class BadBeast extends Entity {
	
	private static final int DEFAULT_ENERGY = -150;
	private int stepCount = 1;
	private int bites = 7;
	
	public BadBeast(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public BadBeast(Board board) {
		super(DEFAULT_ENERGY, board);
	}
	
	public void bites(EntityContext context) {
		bites--;
		if(bites <= 0)
			context.killAndReplace(this);
	}

	public void nextStep(EntityContext context) {
		stepCount--;
		if(stepCount > 0)
			return;
		
		stepCount = 4;
		
		XY squirrelPos = context.nearestSquirrel(getXY()).getXY();
		
		int x, y;
		int diff = squirrelPos.x() - getXY().x();
		if (diff > 0)
			x = 1;
		else if (diff < 0)
			x = -1;
		else 
			x = 0;
		
		diff = squirrelPos.y() - getXY().y();
		if (diff > 0)
			y = 1;
		else if (diff < 0)
			y = -1;
		else 
			y = 0;
		
		context.tryMove(this, new XY(x, y));
	}
	
	public String toString() {
		return "BadBeast" + super.toString();
	}
	
}
