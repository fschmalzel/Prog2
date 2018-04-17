package de.hsa.g17.fatsquirrel.core;

public class State {
	int hightscore;
	Board board;
	
	public State(Board board) {
		this.board = board;
	}
	
	public void update() {
		board.update(flattenedBoard());
	}
	
	public FlattenedBoard flattenedBoard() {
		return new FlattenedBoard(board);
	}

}
