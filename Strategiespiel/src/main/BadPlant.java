package main;

public class BadPlant extends Entity{
	
	private static final int DEFAULT_ENERGY = -100;
	
	public BadPlant(int id, XY xy) {
		super(id, DEFAULT_ENERGY, xy);
	}

}
