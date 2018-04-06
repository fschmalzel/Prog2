package main.entitys;

import main.XY;

abstract class Squirrel extends Entity {

	public Squirrel(int energy, XY xy) {
		super(energy, xy);
	}
	public Squirrel(int energy, int x, int y) {
		super(energy, x, y);
	}
	
}
