package de.hsa.games.fatsquirrel.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.reflections.Reflections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.util.XY;

public class BoardConfig {
	private final static XY DEFAULT_SIZE = new XY(30, 15);
	private final static int DEFAULT_STEPS = 100;
	private static final int DEFAULT_NUM_GOOD_BEAST = 5;
	private static final int DEFAULT_NUM_BAD_BEAST = 5;
	private static final int DEFAULT_NUM_GOOD_PLANT = 13;
	private static final int DEFAULT_NUM_BAD_PLANT = 13;
	private static final int DEFAULT_NUM_WALL = 20;
	private static final boolean DEFAULT_HAND_OPERATED = false;
	
	private final Logger logger = Logger.getLogger(BoardConfig.class.getName());
	private final XY size;
	private final int steps;
	private final int numGoodBeast;
	private final int numBadBeast;
	private final int numGoodPlant;
	private final int numBadPlant;
	private final int numWall;
	private final boolean handOperated;
	private List<String> botNames= new LinkedList<String>();
	private List<Class<? extends BotControllerFactory>> botClasses = new LinkedList<Class<? extends BotControllerFactory>>();
	
	/**
	 * Generates a {@link BoardConfig} with the default values.
	 */
	public BoardConfig() {
		size = DEFAULT_SIZE;
		steps = DEFAULT_STEPS;
		numGoodBeast = DEFAULT_NUM_GOOD_BEAST;
		numBadBeast = DEFAULT_NUM_BAD_BEAST;
		numGoodPlant = DEFAULT_NUM_GOOD_PLANT;
		numBadPlant = DEFAULT_NUM_BAD_PLANT;
		numWall = DEFAULT_NUM_WALL;
		handOperated = DEFAULT_HAND_OPERATED;
		loadAllClasses();
	}

	/**
	 * Generates a {@link BoardConfig} with the values specified in the file.
	 * @param path The file to be loaded.
	 * @throws IOException If there is a problem loading the config file.
	 */
	public BoardConfig(String path) throws IOException {
		
		logger.info("Loading config \"" + path + "\"!");
		
		JsonNode root = null;
		
		try (InputStream is = new FileInputStream(new File(path))) {
			
			ObjectMapper mapper = new ObjectMapper();
			root = mapper.readTree(is);
					
		} catch (FileNotFoundException e) {
			Logger.getLogger(BoardConfig.class.getName()).warning("Config file not found!");
			throw e;
		} catch (IOException e) {
			throw e;
		}
		
		int x, y;
		if (root.has("size")) {
			JsonNode size = root.get("size");
			if (size.has("x") && size.get("x").isInt()) {
				x = size.get("x").asInt();
			} else {
				logger.warning("No size.x specified in config or not an integer, using default!");
				x = DEFAULT_SIZE.x;
			}
			
			if (size.has("y") && size.get("y").isInt()) {
				y = size.get("y").asInt();
			} else {
				logger.warning("No size.y specified in config or not an integer, using default!");
				y = DEFAULT_SIZE.y;
			}
			this.size = new XY(x, y);
		} else {
			logger.warning("No size specified in config, using default!");
			this.size = DEFAULT_SIZE;
		}
		
		if (root.has("steps") && root.get("steps").isInt()) {
			steps = root.get("steps").asInt();
		} else {
			logger.warning("No steps specified in config, using default!");
			steps = DEFAULT_STEPS;
		}
		
		if (root.has("numGoodBeast") && root.get("numGoodBeast").isInt()) {
			numGoodBeast = root.get("numGoodBeast").asInt();
		} else {
			logger.warning("No numGoodBeast specified in config, using default!");
			numGoodBeast = DEFAULT_NUM_GOOD_BEAST;
		}
		
		if (root.has("numBadBeast") && root.get("numBadBeast").isInt()) {
			numBadBeast = root.get("numBadBeast").asInt();
		} else {
			logger.warning("No numBadBeast specified in config, using default!");
			numBadBeast = DEFAULT_NUM_BAD_BEAST;
		}
		
		if (root.has("numGoodPlant") && root.get("numGoodPlant").isInt()) {
			numGoodPlant = root.get("numGoodPlant").asInt();
		} else {
			logger.warning("No numGoodPlant specified in config, using default!");
			numGoodPlant = DEFAULT_NUM_GOOD_PLANT;
		}
		
		if (root.has("numBadPlant") && root.get("numBadPlant").isInt()) {
			numBadPlant = root.get("numBadPlant").asInt();
		} else {
			logger.warning("No numBadPlant specified in config, using default!");
			numBadPlant = DEFAULT_NUM_BAD_PLANT;
		}
		
		if (root.has("numWall") && root.get("numWall").isInt()) {
			numWall = root.get("numWall").asInt();
		} else {
			logger.warning("No numWall specified in config, using default!");
			numWall = DEFAULT_NUM_WALL;
		}
		
		if (root.has("handOperated") && root.get("handOperated").isBoolean()) {
			handOperated = root.get("handOperated").asBoolean();
		} else {
			logger.warning("No handOperated specified in config, using default!");
			handOperated = DEFAULT_HAND_OPERATED;
		}
		
		if (root.has("bots") && root.get("bots").isArray()) {
			for (JsonNode node : root.get("bots")) {
				if (node.isTextual()) {
					botNames.add("de.hsa.games.fatsquirrel.botimpls." + node.asText());
					logger.fine("Added bot \"" + node.asText() + "\" to loading list!");
				}
			}
			if (botNames.isEmpty()) {
				logger.warning("No bots specified in config, scanning for bots!");
				loadAllClasses();
			} else 
				loadClasses();
			
		} else {
			logger.warning("No bots specified in config, scanning for bots!");
			loadAllClasses();
		}
		
	}
	
	/**
	 * Loads all classes in the botimpls package
	 */
	private void loadAllClasses() {
		Reflections reflections = new Reflections("de.hsa.games.fatsquirrel.botimpls");
		Set<Class<? extends BotControllerFactory>> allClasses = reflections.getSubTypesOf(BotControllerFactory.class);
		botClasses.addAll(allClasses);
	}
	
	/**
	 * Loads all classes that are specified by the config file.
	 */
	@SuppressWarnings("unchecked")
	private void loadClasses() {
		logger.fine("Loading bots!");
		ClassLoader loader = BoardConfig.class.getClassLoader();
		for (String name : botNames) {
			try {
				logger.info("Loading Bot \"" + name + "\".");
				botClasses.add((Class<? extends BotControllerFactory>) loader.loadClass(name));
			} catch (ClassNotFoundException e) {
				logger.warning("Couldn't find class with name: \"" + name + "\".");
			} catch (Throwable e) {
				logger.warning("Error loading: \"" + name + "\".");
			}
		}
	}
	
	/**
	 * @return The classes of all bots that are loaded.
	 */
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
		return numBadBeast + numGoodBeast + numGoodPlant + numBadBeast + numWall;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public boolean getHandOperated() {
		return handOperated;
	}
	
	
}
