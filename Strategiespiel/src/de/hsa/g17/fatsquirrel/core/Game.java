package de.hsa.g17.fatsquirrel.core;

public abstract class Game {

	public void run() {
	    while (true) {
	        render();
	        processInput();
	        update();
	    }
	}

	private void update() {
		
	}

	abstract void processInput();

	abstract void render();
}
