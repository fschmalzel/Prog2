package de.hsa.g17.fatsquirrel.core;

import java.util.Random;

import de.hsa.g17.fatsquirrel.entities.BadBeast;
import de.hsa.g17.fatsquirrel.entities.BadPlant;
import de.hsa.g17.fatsquirrel.entities.GoodBeast;
import de.hsa.g17.fatsquirrel.entities.GoodPlant;
import de.hsa.g17.fatsquirrel.entities.Wall;

public class Board {
	int numEntity;
	EntitySet set;
	BoardConfig config;
	
	public Board(BoardConfig config) {
		set = new EntitySet();
		this.config = config;
		
		for(int i = 0; i < config.getWidth(); i++) {
			set.insert(new Wall(i,0));
			set.insert(new Wall(i, config.getHeight()));
		}
		
		for(int i = 1; i < config.getHeight()-1; i++) {
			set.insert(new Wall(0,i));
			set.insert(new Wall(config.getWidth(), i));
		}
				
		for (int i = 0; i < config.getNumBadBeast(); i++) {
			XY xy = randomCoordinates();
			set.insert(new BadBeast(xy));	
		}
		
		for (int i = 0; i < config.getNumGoodBeast(); i++) {
			XY xy = randomCoordinates();
			set.insert(new GoodBeast(xy));	
		}
		
		for (int i = 0; i < config.getNumBadPlant(); i++) {
			XY xy = randomCoordinates();
			set.insert(new BadPlant(xy));	
		}
		
		for (int i = 0; i < config.getNumGoodPlant(); i++) {
			XY xy = randomCoordinates();
			set.insert(new GoodPlant(xy));	
		}
		
		for (int i = 0; i < config.getNumWall(); i++) {
			XY xy = randomCoordinates();
			set.insert(new Wall(xy));	
		}
		
	}
	
	public XY randomCoordinates() {
		Random rnd = new Random();
		
		int x;
		int y;
		//TODO Fix
		
		x = rnd.nextInt(config.getWidth() - 2) + 1;
		y = rnd.nextInt(config.getHeight() - 2) + 1;
		
		return new XY(x,y);
	}
	
	public Entity[][] flatten() {
		
		Entity[][] field = new Entity[config.getWidth()][config.getHeight()];
		
		if (set == null)
			return field;
		
		Entity[] entityArray = set.toArray();
		
		for (Entity e : entityArray) {
			int x = e.getXY().getX();
			int y = e.getXY().getY();
			
			if (x < config.getWidth() && x > 0 && y < config.getHeight() && y > 0) {
				field[x][y] = e;
			}
		}
		
		return field;
		
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
