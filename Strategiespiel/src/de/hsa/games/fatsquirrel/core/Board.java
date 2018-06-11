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

/**
 * Holds all {@link Entity}s, {@link MasterSquirrel}s and the config.
 * Generates the initial entities
 */
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
	
	/**
	 * Updates the board using the supplied EntityContext
	 * 
	 * @param context
	 */
	public void update(EntityContext context) {
		steps--;
		for (Entity e : set.toArray(new Entity[set.size()])) {
			if (e instanceof Character && set.contains(e))
				((Character) e).nextStep(context);
		}
	}
	
	/**
	 * @return  Generates a {@link FlattenedBoard} using this {@link Board}.
	 */
	public FlattenedBoard flatten() {
		return new FlattenedBoard(this);
	}
	
	/**
	 * @param The {@link Entity} to be removed from this {@link Board}.
	 */
	public void remove(Entity e) {
		set.remove(e);
	}
	
	/**
	 * @param The {@link Entity} to be added to this {@link Board}.
	 */
	public void insert(Entity e) {
		set.add(e);
	}
	
	/**
	 * @return The remaining steps of this round.
	 */
	public int getSteps() {
		return steps;
	}
	
	/**
	 * @return All {@link Entity}s in this {@link Board}.
	 */
	public Entity[] getEntitys() {
		return set.toArray(new Entity[set.size()]);
	}
	
	/**
	 * @return All {@link MasterSquirrel} with their names.
	 */
	public Map<String, MasterSquirrel> getMasterSquirrels() {
		return masters;
	}
	
	/**
	 * @return The {@link HandOperatedMasterSquirrel} that is controlled by the player.
	 */
	public HandOperatedMasterSquirrel getPlayer() {
		return player;
	}
	
	/**
	 * @return The {@link BoardConfig} that this {@link Board}.
	 */
	public BoardConfig getConfig() {
		return config;
	}
	
	/**
	 * @return All {@link Entity}s as {@link String}.
	 */
	public String toString() {
		return "Num of Entitys: " + set.size() + "\n" + set;
	}
	
}
