package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.GameCommand;

public interface UI {
	GameCommand getCommand();
	
	void render(BoardView view);

	void message(String msg);
	
}
