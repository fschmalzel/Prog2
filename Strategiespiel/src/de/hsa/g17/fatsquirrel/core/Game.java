package de.hsa.g17.fatsquirrel.core;

public abstract class Game {
	
	protected State state;
	protected UI ui;
	public static final int FPS = 10;
	private boolean synchron;
	
	Game(State state, UI ui, boolean synchron) {
		this.state = state;
		this.ui = ui;
		this.synchron = synchron;
	}
	
	public void run() {
	    while (true) {
	        render();
	        
	        if(!synchron)
		        try {
					Thread.sleep(1000/FPS);
				} catch (InterruptedException e) {}
	        
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
