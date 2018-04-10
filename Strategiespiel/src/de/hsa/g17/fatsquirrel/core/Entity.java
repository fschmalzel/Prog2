package de.hsa.g17.fatsquirrel.core;

public abstract class Entity {
	private final int id;
	private int energy;
	private XY xy;
	private static int lastID = -1;
	
	public Entity(int energy, XY xy) {
		this.id = ++lastID;
		this.energy = energy;
		this.xy = xy;
	}
	
	public Entity(int energy, int x, int y) {
		this(energy, new XY(x, y));
	}
	
	public abstract void nextStep();
	
	public int getEnergy() {
		return energy;
	}
	
	public void updateEnergy(int energy) {
		this.energy += energy;
	}
	
	public XY getXY() {
		return xy;
	}
	
	public void move(XY vector) {
		xy = xy.move(vector);
	}
	
	public int getID() {
		return id;
	}
	
	public boolean isSameType(Entity e) {
		return this.getClass().equals(e.getClass());
	}
	
	public String toString() {
		return " with id " + id + " at " + xy.toString() + " with energy " + energy;
	}
	
}
