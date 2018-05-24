package de.hsa.games.fatsquirrel;

import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.State;

public abstract class Game {
	
	protected State state;
	protected UI ui;
	public static final int FPS = 10;
	
	protected Game(BoardConfig boardConfig, UI ui) {
		state = new State(boardConfig);
		this.ui = ui;
	}
	
	public void run() {
		Logger logger = Logger.getLogger(Launcher.class.getName());
		logger.fine("Game started!");
		while(true) {
			
			render();
			processInput();
			update();
		}
	}

	protected void update() {
		state.update();
	}

	protected abstract void processInput();

	protected abstract void render();
}
