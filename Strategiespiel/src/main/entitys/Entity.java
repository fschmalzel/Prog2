package main.entitys;

import main.XY;

abstract class Entity {
	private final int id;
	private int energy;
	private XY xy;
	
	public Entity(int id, int energy, XY xy) {
		this.id = id;
		this.energy = energy;
		this.xy = xy;
	}
	
	public Entity(int id, int energy, int x, int y) {
		this(id, energy, new XY(x, y));
	}
	
	abstract void nextStep();
	
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
