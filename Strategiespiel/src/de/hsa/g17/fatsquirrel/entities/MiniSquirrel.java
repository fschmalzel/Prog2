package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.XY;

public class MiniSquirrel extends Squirrel {
	
	private final int masterID;
	
	public MiniSquirrel(int energy, XY xy, int masterID) {
		super(energy, xy);
		this.masterID = masterID;
	}
	
	public MiniSquirrel(int energy, int x, int y, int masterID) {
		super(energy, x, y);
		this.masterID = masterID;
	}

	public int getMasterID() {
		return masterID;
	}
	
	public String toString() {
		return "MiniSquirrel" + super.toString();
	}

	@Override
	public void nextStep() {
		this.updateEnergy(-1);
		
		if(this.getEnergy() <= 0) {}
			
	}
	
}
