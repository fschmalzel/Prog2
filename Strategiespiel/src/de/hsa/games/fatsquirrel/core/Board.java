package de.hsa.games.fatsquirrel.core;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.Character;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class Board {
	private Set<Entity> set;
	private BoardConfig config;
	
	public Board(BoardConfig config) {
		set = new LinkedHashSet<Entity>();
		this.config = config;
		
		for(int i = 0; i < config.getSize().x; i++) {
			set.add(new Wall(new XY(i,0)));
			set.add(new Wall(new XY(i, config.getSize().y-1)));
		}
		
		for(int i = 1; i < config.getSize().y-1; i++) {
			set.add(new Wall(new XY(0,i)));
			set.add(new Wall(new XY(config.getSize().x-1, i)));
		}
				
		for (int i = 0; i < config.getNumBadBeast(); i++)
			set.add(new BadBeast(XYsupport.getRandomCoordinates(this)));	
		
		for (int i = 0; i < config.getNumGoodBeast(); i++)
			set.add(new GoodBeast(XYsupport.getRandomCoordinates(this)));
		
		for (int i = 0; i < config.getNumBadPlant(); i++)
			set.add(new BadPlant(XYsupport.getRandomCoordinates(this)));
		
		for (int i = 0; i < config.getNumGoodPlant(); i++)
			set.add(new GoodPlant(XYsupport.getRandomCoordinates(this)));
		
		for (int i = 0; i < config.getNumWall(); i++)
			set.add(new Wall(XYsupport.getRandomCoordinates(this)));
		
	}
	
	public void update(EntityContext context) {
		set.forEach(new Consumer<Entity>() {

			@Override
			public void accept(Entity e) {
				if (e instanceof Character)
					((Character) e).nextStep(context);
			}
		});
	}
	
	public FlattenedBoard flatten() {
		return new FlattenedBoard(this);
	}
	
	public void remove(Entity e) {
		set.remove(e);
	}
	
	public void insert(Entity e) {
		set.add(e);
	}
	
	public Entity[] getEntitys() {
		return (Entity[]) set.toArray();
	}
	
	public BoardConfig getConfig() {
		return config;
	}
	
	public String toString() {
		return "Num of Entitys: " + set.size() + "\n" + set;
	}
	
}
