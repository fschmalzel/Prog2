package de.hsa.games.fatsquirrel.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrelBot;

public class State {
	
	//TODO new highscore
	private Board board;
	private int steps;
	private Map<String, List<Integer>> scores;
	
	MasterSquirrel masterSquirrel;
	
	public State(BoardConfig boardConfig) {
		scores = new HashMap<>();

		steps = boardConfig.getSteps();
		board = new Board(boardConfig);
		
		if (board.getPlayer() != null)
			scores.put("player", new LinkedList<Integer>());
		
		for (MasterSquirrelBot bot : board.getMasterSquirrelBots()) {
			String name = bot.getClass().getName();
			if (name.startsWith("de.hsa.games.fatsquirrel.botimpls.")) {
				name = name.substring("de.hsa.games.fatsquirrel.botimpls.".length());
			}
			scores.put(name,  new LinkedList<Integer>());
		}
	}
	
	private void endRound() {
		if (board.getPlayer() != null)
			scores.get("player").add(board.getPlayer().getEnergy());
		
		for (MasterSquirrelBot bot : board.getMasterSquirrelBots()) {
			String name = bot.getClass().getName();
			if (name.startsWith("de.hsa.games.fatsquirrel.botimpls.")) {
				name = name.substring("de.hsa.games.fatsquirrel.botimpls.".length());
			}
			scores.get(name).add(bot.getEnergy());
		}
		
		List<Map.Entry<String, List<Integer>>> list = new LinkedList<Entry<String, List<Integer>>>(scores.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, List<Integer>>>() {

			@Override
			public int compare(Entry<String, List<Integer>> o1, Entry<String, List<Integer>> o2) {
				int highest1 = 0;
				ListIterator<Integer> i = o1.getValue().listIterator();
				while (i.hasNext()) {
					Integer val = i.next();
					if (val > highest1)
						highest1 = val;
				}
				
				int highest2 = 0;
				ListIterator<Integer> i2 = o2.getValue().listIterator();
				while (i2.hasNext()) {
					Integer val = i2.next();
					if (val > highest2)
						highest2 = val;
				}
				
				return Integer.compare(highest1, highest2);
			}
		});
		
		System.out.println();
		final Logger logger = Logger.getLogger(State.class.getName());
		logger.info("Scores:");
		for (Map.Entry<String, List<Integer>> entry : list) {
			StringBuilder s = new StringBuilder(entry.getKey());
			ListIterator<Integer> i = entry.getValue().listIterator();
			while (i.hasNext()) {
				s.append("\t|\t" + i.next());
			}
			System.out.println(s.toString());
			logger.info(s.toString());
		}
		
		steps = board.getConfig().getSteps();
		board = new Board(board.getConfig());
	}	
	
	public void update() {
		board.update(flattenedBoard());
		
		if (steps-- <= 0)
			endRound();
		
	}
	
	public Board getBoard() {
		return board;
	}
	
	public HandOperatedMasterSquirrel getPlayer() {
		return board.getPlayer();
	}
	
	public FlattenedBoard flattenedBoard() {
		return new FlattenedBoard(board);
	}

	public String getScore() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void finalize() throws Throwable { 
		save();
	};
	
	private void save() {
		JsonFactory factory = new JsonFactory () ;
		try ( OutputStream os = Files.newOutputStream(path) ;
				
			JsonGenerator jg = factory.createGenerator (os) ) {
			
			for(Map.Entry<String, List<Integer>> entry : scores.entrySet()) {
				jg.writeStartObject();
				jg.writeStringField("Bot",entry.getKey());
				jg.writeEndObject();
				
				jg.writeStartArray();
				jg.writeArrayFieldStart("Score");
				int i = 1;
				for(Integer rounds : entry.getValue()) {
					jg.writeNumberField("round " + i, rounds);
					i++;
				}
				jg.writeEndArray();
			}
			
			jg.close();
			
		} catch ( IOException e) {
			e. printStackTrace () ;
		}

	}
	
	private void load() {
		try ( InputStream is = Files.newInputStream(path) ) {
			ObjectMapper mapper = new ObjectMapper () ;
			JsonNode root = mapper . readTree (is);
			
			String botName = root.path("Bot").asText();
			
			List<Integer> botScores = new LinkedList<Integer>();
			
			JsonNode scoreNode = root.path("Score");
			int i = 1
					;
			for(JsonNode node : scoreNode) {
				botScores.add(node.path("round " + i).asInt());
				i++;
			}
			
			scores.put(botName, botScores);

			} catch ( IOException e) {
			e. printStackTrace () ;
			}
	}

}
