package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.BadBeast;
import de.hsa.g17.fatsquirrel.entities.BadPlant;
import de.hsa.g17.fatsquirrel.entities.GoodBeast;
import de.hsa.g17.fatsquirrel.entities.GoodPlant;
import de.hsa.g17.fatsquirrel.entities.Wall;

public class Board {
	private int numEntity;
	private EntitySet set;
	private BoardConfig config;
	
	public Board(BoardConfig config) {
		set = new EntitySet();
		this.config = config;
		
		for(int i = 0; i < config.getSize().x(); i++) {
			set.insert(new Wall(new XY(i,0)));
			set.insert(new Wall(new XY(i, config.getSize().y()-1)));
		}
		
		for(int i = 1; i < config.getSize().y()-1; i++) {
			set.insert(new Wall(new XY(0,i)));
			set.insert(new Wall(new XY(config.getSize().x()-1, i)));
		}
				
		for (int i = 0; i < config.getNumBadBeast(); i++)
			set.insert(new BadBeast(XY.getRandomCoordinates(config.getSize(), set.toArray())));	
		
		for (int i = 0; i < config.getNumGoodBeast(); i++)
			set.insert(new GoodBeast(XY.getRandomCoordinates(config.getSize(), set.toArray())));
		
		for (int i = 0; i < config.getNumBadPlant(); i++)
			set.insert(new BadPlant(XY.getRandomCoordinates(config.getSize(), set.toArray())));
		
		for (int i = 0; i < config.getNumGoodPlant(); i++)
			set.insert(new GoodPlant(XY.getRandomCoordinates(config.getSize(), set.toArray())));
		
		for (int i = 0; i < config.getNumWall(); i++)
			set.insert(new Wall(XY.getRandomCoordinates(config.getSize(), set.toArray())));
		
	}
	
	public void update(EntityContext context) {
		set.nextStep(context);
	}
	
	public FlattenedBoard flatten() {
		return new FlattenedBoard(this);
	}
	
	public void remove(Entity e) {
		set.remove(e);
	}
	
	public void insert(Entity e) {
		set.insert(e);
	}
	
	public Entity[] getEntitys() {
		return set.toArray();
	}
	
	public BoardConfig getConfig() {
		return config;
	}
	
	public String toString() {
		return "Num of Entitys: " + numEntity + "\n" + set;
	}
	
}
