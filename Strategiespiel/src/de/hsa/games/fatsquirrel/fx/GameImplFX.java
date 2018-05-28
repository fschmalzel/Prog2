package de.hsa.games.fatsquirrel.fx;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.bot.random.RandomBotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class GameImplFX extends Game {

	private HandOperatedMasterSquirrel masterSquirrel;

	public GameImplFX(BoardConfig boardConfig, FxUI ui) {
		super(boardConfig, ui);

		// Temporary
		masterSquirrel = new HandOperatedMasterSquirrel(XYsupport.getRandomCoordinates(state.getBoard()));
		
		BotControllerFactory factory = new RandomBotControllerFactory();
		MasterSquirrel masterSquirrel = new MasterSquirrelBot(XYsupport.getRandomCoordinates(state.getBoard()), factory);
		
		state.insertMaster(masterSquirrel);
	}

	@Override
	protected void processInput() {
		GameCommand cmd = ui.getCommand();
		if (cmd == null)
			return;

		switch (cmd.getType()) {
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
	}

	@Override
	protected void render() {
		ui.render(state.flattenedBoard());
		ui.message("Energy: " + masterSquirrel.getEnergy());
	}

}
