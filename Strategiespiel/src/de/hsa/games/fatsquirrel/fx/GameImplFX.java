package de.hsa.games.fatsquirrel.fx;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class GameImplFX extends Game {

	private HandOperatedMasterSquirrel masterSquirrel;
	
	public GameImplFX(BoardConfig boardConfig, FxUI ui) {
		super(boardConfig, ui);

		//Temporary
		masterSquirrel = new HandOperatedMasterSquirrel(
				XYsupport.getRandomCoordinates(state.getBoard()));
		state.insertMaster(masterSquirrel);
	}

	@Override
	protected void processInput() {
		GameCommand cmd = ui.getCommand();
		Launcher.getLogger().log(Level.FINER, "process Input");
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
		Launcher.getLogger().log(Level.FINE, "Game started");
		
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
	
	@Override
	protected void render() {
		Launcher.getLogger().log(Level.FINER, "Board rendered");
		ui.render(state.flattenedBoard());
	}
	
}
