package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;

public class State {
	private int highscore;
	private Board board;
	MasterSquirrel masterSquirrel;
	
	public State() {
		BoardConfig bConfig = new BoardConfig();
		board = new Board(bConfig);
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
