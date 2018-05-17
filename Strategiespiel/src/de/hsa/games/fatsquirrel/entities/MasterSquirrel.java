package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.util.XY;

public abstract class MasterSquirrel extends Squirrel {
	
	private final static int DEFAULT_ENERGY = 1000;
	
	protected MasterSquirrel(XY xy) {
		super(DEFAULT_ENERGY, xy);
	}
	
	@Override
	public void updateEnergy(int energy) {
		if (getEnergy() + energy <= 0)
			super.updateEnergy(-getEnergy());
		else
			super.updateEnergy(energy);
	}
	
	public boolean isChild(Entity e) {
		
		if(e instanceof MiniSquirrel) {
			MiniSquirrel child = (MiniSquirrel) e;
			
			if(child.getMasterID() == this.getID())
				return true;
		}
		
		return false;
	}

}
