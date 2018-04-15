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
	
	public XY randomCoordinates() {
		Random rnd = new Random();
		
		int x;
		int y;
		//TODO Look for duplicates on the field
		
		
		x = rnd.nextInt(config.getSize().x() - 2) + 1;
		y = rnd.nextInt(config.getSize().y() - 2) + 1;
		
		return new XY(x,y);
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
		
//		for(int y = 0; y < set[0].length; y++) {
//			s += "\n";
//			for(int x = 0; x < set.length; x++) {
//				char c;
//				if (set[x][y] instanceof GoodBeast) {
//					c = 'G';
//				} else if (set[x][y] instanceof BadBeast) {
//					c = 'B';
//				} else if (set[x][y] instanceof GoodPlant) {
//					c = 'g';
//				} else if (set[x][y] instanceof BadPlant) {
//					c = 'b';
//				} else if (set[x][y] instanceof Wall) {
//					c = '#';
//				} else if (set[x][y] instanceof MasterSquirrel) {
//					c = 'M';
//				} else if (set[x][y] instanceof MiniSquirrel) {
//					c = 'm';
//				} else {
//					c = '.';
//				}
//				
//				s += c;
//			}
//		}
//		return s;
		
	}
	
	
}
