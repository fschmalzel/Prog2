package main.entitys;

import main.XY;

public class MiniSquirrel extends Squirrel {
	
	private final int masterID;
	
	public MiniSquirrel(int id, int energy, XY xy, int masterID) {
		super(id, energy, xy);
		this.masterID = masterID;
	}
	
	public MiniSquirrel(int id, int energy, int x, int y, int masterID) {
		super(id, energy, x, y);
		this.masterID = masterID;
	}

	public int getMasterID() {
		return masterID;
	}
	
	public String toString() {
		return "MiniSquirrel" + super.toString();
	}

	@Override
	void nextStep() {}
	
}
