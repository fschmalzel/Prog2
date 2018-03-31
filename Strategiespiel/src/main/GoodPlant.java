package main;

public class GoodPlant extends Entity{
	
	private static final int DEFAULT_ENERGY = 100;
	
	public GoodPlant(int id, XY xy) {
		super(id, DEFAULT_ENERGY, xy);
	}
}
