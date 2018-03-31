package main;

public class BadBeast extends Entity {
	
	private static final int DEFAULT_ENERGY = -150;
	
	public BadBeast(int id, XY xy) {
		super(id, DEFAULT_ENERGY, xy);
	}

}
