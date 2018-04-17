package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.EntityContext;
import de.hsa.g17.fatsquirrel.core.XY;

public abstract class Squirrel extends Character {

	private int stunnedRounds = 0;
	
	protected Squirrel(int energy, XY xy) {
		super(energy, xy);
	}
	
	public void stun() {
		stunnedRounds = 3;
	}
	
	@Override
	public void nextStep(EntityContext context) {
		if (stunnedRounds > 0)
			stunnedRounds--;
	}
	
	public boolean isStunned() {
		
		if (stunnedRounds > 0) {
			return true;
		}
		
		return false;
		
	}
	
}
