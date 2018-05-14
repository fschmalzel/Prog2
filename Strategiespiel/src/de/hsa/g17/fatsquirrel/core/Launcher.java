package de.hsa.g17.fatsquirrel.core;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher {

	public static void main(String[] args) {
		
		if (args.length >= 1)
			if (args[0].equalsIgnoreCase("singleThread=true")) {
				(new GameImpl(true)).run();
				return;
			}
		startGame(new GameImpl(false));
		
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
