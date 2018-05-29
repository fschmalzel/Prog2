package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.entities.MasterSquirrel;

public class State {
	private int highscore;
	private Board board;
	MasterSquirrel masterSquirrel;
	
	public State(BoardConfig boardConfig) {
		board = new Board(boardConfig);
	}
	
	public void insertMaster(MasterSquirrel s) {
		masterSquirrel = s;
		board.insert(s);
	}
	
	public void update() {
		board.update(flattenedBoard());
		if (masterSquirrel != null) {
			if (masterSquirrel.getEnergy() > highscore)
				highscore = masterSquirrel.getEnergy();
		}
		
	}
	
	public int getHighscore() {
		return highscore;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public FlattenedBoard flattenedBoard() {
		return new FlattenedBoard(board);
	}

}
