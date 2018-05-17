package de.hsa.games.fatsquirrel.core;

public class GameCommand {

	public static enum Type {
		MOVE, SPAWN, MASTERENERGY, ALL;
	}
	
	private Type t;
	
	public GameCommand(Type t) {
		this.t = t;
	}
	
	public Type getType() {
		return t;
	}
	
}
