package de.hsa.g17.fatsquirrel.core;

public class BoardConfig {
	private final XY size = new XY(30, 15);
	private final int numBadBeast = 5;
	private final int numGoodBeast = 5;
	private final int numGoodPlant = 13;
	private final int numBadPlant = 13;
	private final int numWall = 20;
	private final int numEntity = numBadBeast + numGoodBeast + numGoodPlant + numBadBeast + numWall;
	
	public XY getSize() {
		return size;
	}
	
	public int getNumBadBeast() {
		return numBadBeast;
	}
	
	public int getNumGoodBeast() {
		return numGoodBeast;
	}
	
	public int getNumGoodPlant() {
		return numGoodPlant;
	}
	
	public int getNumBadPlant() {
		return numBadPlant;
	}
	
	public int getNumWall() {
		return numWall;
	}
	
	public int getEntity() {
		return numEntity;
	}
	
	
}
