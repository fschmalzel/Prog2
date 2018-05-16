package de.hsa.g17.fatsquirrel.core;

public interface UI {
	GameCommand getCommand();
	
	void render(BoardView view);

	void message(String msg);

	GameCommand getCommandUnsyn();
	
}
