package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.Entity;
import de.hsa.g17.fatsquirrel.core.MoveCommand;
import de.hsa.g17.fatsquirrel.core.XY;

public abstract class MasterSquirrel extends Squirrel {
	
	private final static int DEFAULT_ENERGY = 1000;
	
	protected MoveCommand moveCommand;
	
	protected MasterSquirrel(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	public boolean isChild(Entity e) {
		
		if(e instanceof MiniSquirrel) {
			MiniSquirrel child = (MiniSquirrel) e;
			
			if(child.getMasterID() == this.getID())
				return true;
		}
		
		return false;
	}

	public void setMoveCommand(MoveCommand moveCommand) {
		this.moveCommand = moveCommand;
	}

}
