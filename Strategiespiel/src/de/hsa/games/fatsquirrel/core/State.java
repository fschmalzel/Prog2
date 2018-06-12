package de.hsa.games.fatsquirrel.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;

/**
 * Saves, loads and controls scores.
 */
public class State {
	private Board board;
	private Map<String, List<Integer>> scores;

	MasterSquirrel masterSquirrel;

	public State(BoardConfig boardConfig) {
		scores = new HashMap<>();
		board = new Board(boardConfig);

		load();
		
		for (Map.Entry<String, MasterSquirrel> e : board.getMasterSquirrels().entrySet()) {
			if (!scores.containsKey(e.getKey())) {
				List<Integer> list = new LinkedList<>();
				list.add(0);
				scores.put(e.getKey(), list);
			}
			
		}
	}

	private void endRound() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			Logger.getLogger(State.class.getName()).warning("Error pausing thread after end of the round!");
		}

		for (Map.Entry<String, MasterSquirrel> e : board.getMasterSquirrels().entrySet()) {
			int score = e.getValue().getEnergy();
			if (score > scores.get(e.getKey()).get(0)) {
				scores.get(e.getKey()).remove(0);
				scores.get(e.getKey()).add(0, score);
			}
			scores.get(e.getKey()).add(score);
		}

		List<Map.Entry<String, List<Integer>>> list = new LinkedList<Entry<String, List<Integer>>>(scores.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, List<Integer>>>() {

			@Override
			public int compare(Entry<String, List<Integer>> o1, Entry<String, List<Integer>> o2) {
				return Integer.compare(o2.getValue().get(0), o1.getValue().get(0));
			}
		});

		save();

		StringBuilder out = new StringBuilder();
		Formatter formatter = new Formatter(out);
		formatter.format("%1$20s", "Name").flush();
		out.append("  |  ");
		formatter.format("%1$9s", "Highscore");
		
		java.util.Iterator<List<Integer>> iter = scores.values().iterator();
		if (iter.hasNext()) {
			int size = iter.next().size()-1;
			for (int i = 0; i < size; i++) {
				out.append("  |  ");
				formatter.format("%1$9s", "Round " + (i+1));
			}
		}
		formatter.flush();
		
		for (Map.Entry<String, List<Integer>> entry : list) {
			out.append('\n');
			formatter.format("%1$20s", entry.getKey()).flush();
			ListIterator<Integer> i = entry.getValue().listIterator();
			while (i.hasNext()) {
				out.append("  |  ");
				formatter.format("%1$9d", i.next()).flush();
			}
		}
		
		formatter.close();
		
		System.out.println(out.toString());
		final Logger logger = Logger.getLogger(State.class.getName());
		logger.info("Scores:\n" + out.toString());
		
		board = new Board(board.getConfig());
	}

	public void update() {
		board.update(flattenedBoard());

		if (board.getSteps() <= 0)
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

	public String getCurrentScore() {
		StringBuilder s = new StringBuilder();
		for (Map.Entry<String, MasterSquirrel> e : board.getMasterSquirrels().entrySet()) {
			s.append(e.getKey()).append(":\t").append(e.getValue().getEnergy()).append("\t|\t");
		}
		return s.toString();
	}

	protected void finalize() throws Throwable {
		save();
	};

	private void save() {
		JsonFactory factory = new JsonFactory();
		
		try (OutputStream os = Files.newOutputStream(Paths.get("highscores.json"))) {

			JsonGenerator jg = factory.createGenerator(os);
			
			jg.writeStartArray();
			for (Map.Entry<String, List<Integer>> entry : scores.entrySet()) {
				jg.writeStartObject();
				jg.writeStringField("Name", entry.getKey());
				
				int highest = 0;
				for (Integer val : entry.getValue()) {
					if (val > highest)
						highest = val;
				}
				jg.writeNumberField("Highscore", highest);
				
				jg.writeEndObject();
			}
			jg.writeEndArray();
			jg.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void load() {
		File file = new File("highscores.json");
		
		if (file.exists())
			try (InputStream is = new FileInputStream(file)) {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(is);
				
				if(root != null && root.isArray()) {
					for (JsonNode node : root) {
						if (node.has("Name") && node.has("Highscore")) {
							if (node.get("Name").isTextual() && node.get("Highscore").isNumber()) {
								List<Integer> list = new LinkedList<>();
								list.add(node.get("Highscore").asInt());
								scores.put(node.get("Name").asText(), list);
							}
							
						}
						
					}
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
