package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

public class MiniSquirrel extends Squirrel {
	
	private final int masterID;
	
	public MiniSquirrel(int energy, XY xy, int masterID) {
		super(energy, xy);
		this.masterID = masterID;
	}

	public int getMasterID() {
		return masterID;
	}
	
	public String toString() {
		return "MiniSquirrel" + super.toString();
	}

	@Override
	public void nextStep(EntityContext context) {
		super.nextStep(context);
		if (isStunned())
			return;
		
		updateEnergy(-1);
		if(getEnergy() <= 0)
			context.kill(this);
			
	}
	
}
