package de.hsa.g17.fatsquirrel.core;

public abstract class Game {
	
	protected State state;
	
	Game(State state) {
		this.state = state;
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
