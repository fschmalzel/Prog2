package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;

public class State {
	int hightscore;
	Board board;
	
	public State() {
		BoardConfig bConfig = new BoardConfig();
		board = new Board(bConfig);
	}
	
	public void insertMaster(MasterSquirrel s) {
		board.insert(s);
	}
	
	public void update() {
		board.update(flattenedBoard());
	}
	
	public FlattenedBoard flattenedBoard() {
		return new FlattenedBoard(board);
	}

}
