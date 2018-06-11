package de.hsa.games.fatsquirrel.fx;

import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.GameCommand;

public class GameImplFX extends Game {

	public GameImplFX(BoardConfig boardConfig, FxUI ui) {
		super(boardConfig, ui);
	}

	@Override
	protected void processInput() {
		GameCommand cmd = ui.getCommand();
		if (cmd == null)
			return;
		
		switch (cmd.getType()) {
		case MASTERENERGY:
			if (state.getPlayer() != null)
				ui.message("master energy: " + state.getPlayer().getEnergy());
			else {
				ui.message("No HandOperatedMasterSquirrel in the game!");
				Logger.getLogger(GameImplFX.class.getName()).warning("Player tried command \"masterenergy\" without any HandOperatedMasterSquirrel!");
			}
			break;
		case ALL:
			ui.message(state.getBoard().toString());
			break;
		default:
			if (state.getPlayer() != null)
				state.getPlayer().setCommand(cmd);
			return;
		}
	}

	@Override
	public void run() {
		asyncRun();
	}

	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
		ui.message(state.getCurrentScore());
	}

}
