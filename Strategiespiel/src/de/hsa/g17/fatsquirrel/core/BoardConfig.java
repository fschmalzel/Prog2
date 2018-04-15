package de.hsa.g17.fatsquirrel.core;

public class BoardConfig {
	private final XY size = new XY(12, 10);
	private final int numBadBeast = 3;
	private final int numGoodBeast = 3;
	private final int numGoodPlant = 2;
	private final int numBadPlant = 5;
	private final int numWall = 5;
	
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
	
	
}
