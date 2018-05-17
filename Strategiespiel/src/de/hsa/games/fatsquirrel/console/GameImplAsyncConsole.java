package de.hsa.games.fatsquirrel.console;

import java.util.Timer;
import java.util.TimerTask;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class GameImplAsyncConsole extends Game {

	private HandOperatedMasterSquirrel masterSquirrel;
	
	public GameImplAsyncConsole(BoardConfig boardConfig) {
		super(boardConfig, new ConsoleUIAsync());

		//Temporary
		masterSquirrel = new HandOperatedMasterSquirrel(
				XYsupport.getRandomCoordinates(state.getBoard()));
		state.insertMaster(masterSquirrel);
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
		
		if(ui instanceof ConsoleUIAsync)
			((ConsoleUIAsync) ui).process();

	}
	
	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
	}
	
}
