package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;

public class Implosion {
	
	public final XY xy;
	public final int size;
	private int lifetime = 10;
	
	public Implosion(XY xy, int size) {
		this.xy = xy;
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean update() {
		lifetime -= 1;
		return lifetime < 0;
	}

}
