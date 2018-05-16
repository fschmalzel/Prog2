package de.hsa.g17.fatsquirrel.core;

import java.util.Timer;
import java.util.TimerTask;

public class GameImplAsync extends GameImpl {

	GameImplAsync(BoardConfig boardConfig, UI ui) {
		super(boardConfig, ui);
	}

	@Override
	protected void processInput() {
		GameCommand cmd = ui.getCommand();
		
		if(cmd == null)
			return;
		
		switch(cmd.getType()) {
		case MASTERENERGY:
			ui.message("master energy: " + masterSquirrel.getEnergy());
			break;
		case ALL:
			ui.message(state.getBoard().toString());
			break;
		default:
			masterSquirrel.setCommand(cmd);
			return;
		}
	}
	
	@Override
	public void run() {
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				while (true) {
			        render();
			        try {
						Thread.sleep(1000/FPS);
					} catch (InterruptedException e) {}
			        processInput();
			        update();
			    }
			}
		}, 0);

	}
	
}
