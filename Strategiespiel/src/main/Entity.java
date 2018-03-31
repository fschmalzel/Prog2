package main;

abstract class Entity {
	private final int id;
	private int energy;
	private XY xy;
	
	public Entity(int id, int energy, XY xy) {
		this.id = id;
		this.energy = energy;
		this.xy = xy;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void updateEnergy(int energy) {
		this.energy += energy;
	}
	
	public XY getXY() {
		return xy;
	}
	
	public void setXY(XY xy) {
		this.xy = xy;
	}
	
	public int getId() {
		return id;
	}
	
	
}
