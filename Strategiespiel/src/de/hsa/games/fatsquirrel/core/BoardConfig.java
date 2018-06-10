package de.hsa.games.fatsquirrel.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.util.XY;

public class BoardConfig {
	private final XY size = new XY(30, 15);
	private final int steps = 10;
	private final int numBadBeast = 5;
	private final int numGoodBeast = 5;
	private final int numGoodPlant = 13;
	private final int numBadPlant = 13;
	private final int numWall = 20;
	private final int numEntity = numBadBeast + numGoodBeast + numGoodPlant + numBadBeast + numWall;
	private List<String> botNames= new LinkedList<String>();
	private List<Class<? extends BotControllerFactory>> botClasses = new LinkedList<Class<? extends BotControllerFactory>>();
	private final boolean handOperated = false;
	
	public BoardConfig() {
		//TODO loadConfig
//		botNames.add("gruppe17.HunterBotControllerFactory");
//		botNames.add("gruppe17.RandomBotControllerFactory");
		
		loadClasses();
	}
	
	public BoardConfig(String path) {
		try (InputStream is = new FileInputStream(new File(path))) {

			Preferences prefs = Preferences.importPreferences(is);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadClasses() {
		ClassLoader loader = Game.class.getClassLoader();
		for (String name : botNames) {
			try {
				botClasses.add((Class<? extends BotControllerFactory>) loader.loadClass("de.hsa.games.fatsquirrel.botimpls." + name));
			} catch (ClassNotFoundException e) {
				Logger.getLogger(BoardConfig.class.getName()).warning("Couldn't find class with name: \"" + "de.hsa.games.fatsquirrel.botimpls." + name + "\"");
			} catch (Throwable e) {
				Logger.getLogger(BoardConfig.class.getName()).warning("Error loading: \"" + "de.hsa.games.fatsquirrel.botimpls." + name + "\"");
			}
		}
	}
	
	public List<Class<? extends BotControllerFactory>> getBots() {
		return botClasses;
	}
	
	public XY getSize() {
		return size;
	}
	
	public int getNumBadBeast() {
		return numBadBeast;
	}
	
	public int getNumGoodBeast() {
		return numGoodBeast;
	}
	
	public int getNumGoodPlant() {
		return numGoodPlant;
	}
	
	public int getNumBadPlant() {
		return numBadPlant;
	}
	
	public int getNumWall() {
		return numWall;
	}
	
	public int getEntity() {
		return numEntity;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public boolean getHandOperated() {
		return handOperated;
	}
	
	
}
