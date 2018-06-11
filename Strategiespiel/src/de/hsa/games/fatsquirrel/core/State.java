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

public class State {
	private Board board;
	private Map<String, List<Integer>> scores;

	MasterSquirrel masterSquirrel;

	public State(BoardConfig boardConfig) {
		scores = new HashMap<>();
		board = new Board(boardConfig);

		load();
		
		for (Map.Entry<String, MasterSquirrel> e : board.getMasterSquirrels().entrySet()) {
			if (!scores.containsKey(e.getKey()))
				scores.put(e.getKey(), new LinkedList<Integer>());
		}
	}

	private void endRound() {

		for (Map.Entry<String, MasterSquirrel> e : board.getMasterSquirrels().entrySet()) {
			scores.get(e.getKey()).add(e.getValue().getEnergy());
		}

		List<Map.Entry<String, List<Integer>>> list = new LinkedList<Entry<String, List<Integer>>>(scores.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, List<Integer>>>() {

			@Override
			public int compare(Entry<String, List<Integer>> o1, Entry<String, List<Integer>> o2) {
				int highest1 = 0;
				for (Integer val : o1.getValue())
					if (val > highest1)
						highest1 = val;

				int highest2 = 0;
				for (Integer val : o2.getValue())
					if (val > highest2)
						highest2 = val;

				return Integer.compare(highest1, highest2);
			}
		});

		save();

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
				jg.writeFieldName("Scores");
				jg.writeStartArray();
				
				for (Integer points : entry.getValue())
					jg.writeNumber(points);
				
				jg.writeEndArray();
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
						if (node.has("Name") && node.has("Scores")) {
							if (node.get("Name").isTextual() && node.get("Scores").isArray()) {
								List<Integer> list = new LinkedList<>();
								for (JsonNode val : node.get("Scores")) {
									if (val.isInt())
										list.add(val.asInt());
								}
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
