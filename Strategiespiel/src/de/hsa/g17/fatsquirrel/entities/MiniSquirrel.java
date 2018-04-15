package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.EntityContext;
import de.hsa.g17.fatsquirrel.core.XY;

public class MiniSquirrel extends Squirrel {
	
	private final int masterID;
	
	public MiniSquirrel(int energy, XY xy, int masterID) {
		super(energy, xy);
		this.masterID = masterID;
	}

	public int getMasterID() {
		return masterID;
	}
	
	public void updateEnergy(int energy, EntityContext context) {
		super.updateEnergy(energy);
		if(getEnergy() <= 0)
			context.kill(this);
	}
	
	public String toString() {
		return "MiniSquirrel" + super.toString();
	}

	@Override
	public void nextStep(EntityContext context) {
		super.nextStep(context);
		if (isStunned())
			return;
		
		updateEnergy(-1, context);
			
	}
	
}
