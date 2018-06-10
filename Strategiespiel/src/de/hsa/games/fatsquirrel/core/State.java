package de.hsa.games.fatsquirrel.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrelBot;

public class State {
	
	//TODO new highscore
	private Board board;
	private int steps;
	private Map<String, Integer> scores;
	
	MasterSquirrel masterSquirrel;
	
	public State(BoardConfig boardConfig) {
		scores = new HashMap<>();

		steps = boardConfig.getSteps();
		board = new Board(boardConfig);
	}
	
	private void endRound() {
		if (board.getPlayer() != null)
			scores.put("player", board.getPlayer().getEnergy());
		
		for (MasterSquirrelBot bot : board.getMasterSquirrelBots()) {
			String name = bot.getClass().getName();
			if (name.startsWith("de.hsa.games.fatsquirrel.botimpls.")) {
				name = name.substring("de.hsa.games.fatsquirrel.botimpls.".length());
			}
			scores.put(name, bot.getEnergy());
		}
		
		List<Map.Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(scores.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (o1.getValue() > o2.getValue())
					return 1;
				else if (o1.getValue() < o2.getValue())
					return -1;
				return 0;
			}
		});
		
		System.out.println();
		final Logger logger = Logger.getLogger(State.class.getName());
		logger.info("Scores:");
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + "\t|\t" + entry.getValue());
			logger.info(entry.getKey() + "\t|\t" + entry.getValue());
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

}
