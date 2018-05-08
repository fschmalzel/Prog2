package de.hsa.g17.fatsquirrel.core;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher {

	public static void main(String[] args) {
		startGame(new GameImpl());
	}
	
	
	private static void startGame(Game game) {
		Timer t = new Timer();
		
		t.schedule(new TimerTask() {
		
			@Override
			public void run() {
				game.run();
			}
		}, 0);
		
		game.ui.process();
	}

}
