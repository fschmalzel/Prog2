package de.hsa.g17.fatsquirrel.core;

public abstract class Game {
	
	protected State state;
	protected UI ui;
	public static final int FPS = 10;
	
	Game(BoardConfig boardConfig, UI ui) {
		state = new State(boardConfig);
		this.ui = ui;
	}
	
	
	public void asynchronizedRun() {
		while (true) {
	        render();
            try {
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {}
	        processInput();
	        update();
	    }
	}
	
	public void synchronizedRun() {
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
