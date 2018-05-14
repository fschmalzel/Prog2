package de.hsa.g17.fatsquirrel.core;

public abstract class Game {
	
	protected State state;
	protected UI ui;
	public static final int FPS = 10;
	private boolean synchron;
	
	Game(BoardConfig boardConfig, UI ui, boolean synchron) {
		state = new State(boardConfig);
		this.ui = ui;
		this.synchron = synchron;
	}
	
	public void run() {
	    if(synchron)
	    	synchronizedRun();
	    else
	    	asynchronizedRun();
	}
	
	private void asynchronizedRun() {
		while (true) {
	        render();
            try {
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {}
	        processInput();
	        update();
	    }
	}
	
	private void synchronizedRun() {
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
