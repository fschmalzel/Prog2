package main.entitys;

import main.XY;

abstract class Squirrel extends Entity {

	public Squirrel(int id, int energy, XY xy) {
		super(id, energy, xy);
	}
	public Squirrel(int id, int energy, int x, int y) {
		super(id, energy, x, y);
	}
	
}
