package de.hsa.games.fatsquirrel.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.entities.Character;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class Board {
	private Set<Entity> set;
	private Map<String, MasterSquirrel> masters;
	private HandOperatedMasterSquirrel player;
	private BoardConfig config;
	private int steps;
	
	public Board(BoardConfig config) {
		set = new HashSet<Entity>();
		masters = new HashMap<>();
		steps = config.getSteps();
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
			set.add(new BadBeast(getRandomValidCoordinates()));	
		
		for (int i = 0; i < config.getNumGoodBeast(); i++)
			set.add(new GoodBeast(getRandomValidCoordinates()));
		
		for (int i = 0; i < config.getNumBadPlant(); i++)
			set.add(new BadPlant(getRandomValidCoordinates()));
		
		for (int i = 0; i < config.getNumGoodPlant(); i++)
			set.add(new GoodPlant(getRandomValidCoordinates()));
		
		for (int i = 0; i < config.getNumWall(); i++)
			set.add(new Wall(getRandomValidCoordinates()));
		
		for (Class<? extends BotControllerFactory> clazz : config.getBots()) {
			try {
				BotControllerFactory factory = clazz.newInstance();
				MasterSquirrelBot m = new MasterSquirrelBot(getRandomValidCoordinates(), factory);
				set.add(m);
				String name = clazz.getCanonicalName();
				if (name.startsWith("de.hsa.games.fatsquirrel.botimpls.")) {
					name = name.substring("de.hsa.games.fatsquirrel.botimpls.".length());
				}
				if (name.endsWith("ControllerFactory")) {
					name = name.substring(0, name.length()-"ControllerFactory".length());
				}
				masters.put(name, m);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		if (config.getHandOperated()) {
			HandOperatedMasterSquirrel m = new HandOperatedMasterSquirrel(getRandomValidCoordinates());
			set.add(m);
			masters.put("HandOperated", m);
			player = m;
		}
		
	}
	private XY getRandomValidCoordinates() {
		do {
			XY xy = XYsupport.getRandomCoordinates(new XY(1, 1), config.getSize().minus(new XY(1, 1)));
			
			boolean badPos = false;
			
			for (Entity e : getEntitys())
				if (e.getXY().equals(xy)) {
					badPos = true;
					break;
				}
			
			if(!badPos)
				return xy;
			
		} while(true);
	}
	
	public void update(EntityContext context) {
		steps--;
		for (Entity e : set.toArray(new Entity[set.size()])) {
			if (e instanceof Character && set.contains(e))
				((Character) e).nextStep(context);
		}
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
	
	public int getSteps() {
		return steps;
	}
	
	public Entity[] getEntitys() {
		return set.toArray(new Entity[set.size()]);
	}
	
	public Map<String, MasterSquirrel> getMasterSquirrels() {
		return masters;
	}
	
	public HandOperatedMasterSquirrel getPlayer() {
		return player;
	}
	
	public BoardConfig getConfig() {
		return config;
	}
	
	public String toString() {
		return "Num of Entitys: " + set.size() + "\n" + set;
	}
	
}
