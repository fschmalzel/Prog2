package de.hsa.g17.fatsquirrel.core;

public abstract class Game {
	
	protected State state;
	protected UI ui;
	
	Game(State state, UI ui) {
		this.state = state;
		this.ui = ui;
	}
	
	public void run() {
	    while (true) {
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
