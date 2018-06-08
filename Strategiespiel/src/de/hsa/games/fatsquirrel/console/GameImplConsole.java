package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;

public class GameImplConsole extends Game {
	
	protected HandOperatedMasterSquirrel masterSquirrel;
	
	public GameImplConsole(BoardConfig boardConfig) {
		super(boardConfig, new ConsoleUI());		
	}
	
	@Override
	protected void processInput() {
		while(true) {
			GameCommand cmd = ui.getCommand();
			
			if(cmd == null)
				continue;
			
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
	}

	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
	}

}
