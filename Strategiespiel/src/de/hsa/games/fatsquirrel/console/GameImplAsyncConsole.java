package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;

public class GameImplAsyncConsole extends Game {

	private HandOperatedMasterSquirrel masterSquirrel;
	
	public GameImplAsyncConsole(BoardConfig boardConfig) {
		super(boardConfig, new ConsoleUIAsync());
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
		
		asyncRun();
		
		if(ui instanceof ConsoleUIAsync)
			((ConsoleUIAsync) ui).process();

	}
	
	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
	}
	
}
