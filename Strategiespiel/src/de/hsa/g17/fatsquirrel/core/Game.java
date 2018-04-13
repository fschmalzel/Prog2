package de.hsa.g17.fatsquirrel.core;

public abstract class Game {

	Game(State state) {
		//TODO save state or sth.
	}
	
	public void run() {
	    while (true) {
	        render();
	        processInput();
	        update();
	    }
	}

	protected void update() {
		
	}

	protected abstract void processInput();

	protected abstract void render();
}
