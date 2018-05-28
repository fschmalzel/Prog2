package de.hsa.games.fatsquirrel;

import java.util.Timer;
import java.util.TimerTask;
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
		logger.info("Game started!");
		while(true) {
			Launcher.getLogger().finer("Rendering");
			render();
			Launcher.getLogger().finer("Processing Input");
			processInput();
			Launcher.getLogger().finer("Updating state");
			update();
		}
	}
	
	protected void asyncRun() {
		Launcher.getLogger().info("Game started!");

		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				while (true) {
					Launcher.getLogger().finer("Rendering");
					render();
					try {
						Thread.sleep(1000 / FPS);
					} catch (InterruptedException e) {
					}
					Launcher.getLogger().finer("Processing Input");
					processInput();
					Launcher.getLogger().finer("Updating state");
					update();
				}
			}
		}, 0);
	}

	protected void update() {
		state.update();
	}

	protected abstract void processInput();

	protected abstract void render();
}
