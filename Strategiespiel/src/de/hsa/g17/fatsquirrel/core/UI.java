package de.hsa.g17.fatsquirrel.core;

public interface UI {
	MoveCommand getCommand();
	
	void render(BoardView view);
}
