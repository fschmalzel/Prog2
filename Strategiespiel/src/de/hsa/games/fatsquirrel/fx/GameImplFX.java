package de.hsa.games.fatsquirrel.fx;

import java.util.LinkedList;
import java.util.List;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botimpls.gruppe17.HunterBotControllerFactory;
import de.hsa.games.fatsquirrel.botimpls.gruppe17.RandomBotControllerFactory;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class GameImplFX extends Game {

	private HandOperatedMasterSquirrel masterSquirrel;
	private List<MasterSquirrel> squirrels = new LinkedList<>();

	public GameImplFX(BoardConfig boardConfig, FxUI ui) {
		super(boardConfig, ui);

		// Temporary
		masterSquirrel = new HandOperatedMasterSquirrel(XYsupport.getRandomCoordinates(state.getBoard()));
		squirrels.add(masterSquirrel);
		
		BotControllerFactory factory = new HunterBotControllerFactory();
		squirrels.add(new MasterSquirrelBot(XYsupport.getRandomCoordinates(state.getBoard()), factory));
		
		BotControllerFactory factory2 = new RandomBotControllerFactory();
		squirrels.add(new MasterSquirrelBot(XYsupport.getRandomCoordinates(state.getBoard()), factory2));
		
		for (MasterSquirrel s : squirrels)
			state.insertMaster(s);
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
			if (masterSquirrel != null)
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
		
		String s = "";

		for (MasterSquirrel squirrel : squirrels)
			s += "Energy: " + squirrel.getEnergy() + "\t\t";
		
		ui.message(s);
	}

}
