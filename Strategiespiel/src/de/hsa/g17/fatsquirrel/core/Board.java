package de.hsa.g17.fatsquirrel.core;

import java.util.Random;

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
			set.insert(new Wall(new XY(i, config.getSize().y())));
		}
		
		for(int i = 1; i < config.getSize().y()-1; i++) {
			set.insert(new Wall(new XY(0,i)));
			set.insert(new Wall(new XY(config.getSize().x(), i)));
		}
				
		for (int i = 0; i < config.getNumBadBeast(); i++)
			set.insert(new BadBeast(this));	
		
		for (int i = 0; i < config.getNumGoodBeast(); i++)
			set.insert(new GoodBeast(this));
		
		for (int i = 0; i < config.getNumBadPlant(); i++)
			set.insert(new BadPlant(this));
		
		for (int i = 0; i < config.getNumGoodPlant(); i++)
			set.insert(new GoodPlant(this));
		
		for (int i = 0; i < config.getNumWall(); i++)
			set.insert(new Wall(this));
		
	}
	
	public void update(EntityContext context) {
		set.nextStep(context);
	}
	
	public XY randomCoordinates() {
		Random rnd = new Random();
		int x, y;
		boolean notFree;
		XY xy;
		
		do {
			notFree = false;
			
			x = rnd.nextInt(config.getSize().x() - 2) + 1;
			y = rnd.nextInt(config.getSize().y() - 2) + 1;
			
			xy = new XY(x,y);
			
			for (Entity e : set.toArray())
				if (e.getXY().equals(xy))
					notFree = true;
			
		} while (notFree);
		
		return xy;
	}
	
	public Entity[][] flatten() {
		
		Entity[][] field = new Entity[config.getSize().x()][config.getSize().y()];
		
		if (set == null)
			return field;
		
		Entity[] entityArray = set.toArray();
		
		for (Entity e : entityArray) {
			int x = e.getXY().x();
			int y = e.getXY().y();
			
			if (x < config.getSize().x() && x > 0 && y < config.getSize().y() && y > 0) {
				field[x][y] = e;
			}
		}
		
		return field;
		
	}
	
	public void remove(Entity e) {
		set.remove(e);
	}
	
	public void insert(Entity e) {
		set.insert(e);
	}
	
	public BoardConfig getConfig() {
		return config;
	}
	
	public String toString() {
		return "Num of Entitys: " + numEntity + "\n" + set;
	}

	public MoveCommand getCommand() {
		return ;
	}
	
	
}
