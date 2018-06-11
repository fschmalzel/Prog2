package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.GameCommand;

public interface UI {
	/**
	 * @return A {@link GameCommand} that represents user input.
	 */
	GameCommand getCommand();
	
	/**
	 * Renders everything
	 * 
	 * @param view of the board which shall be rendered
	 */
	void render(BoardView view);
	
	/**
	 * Displays a simple string on the screen
	 * 
	 * @param msg The message to display
	 */
	void message(String msg);
	
}
