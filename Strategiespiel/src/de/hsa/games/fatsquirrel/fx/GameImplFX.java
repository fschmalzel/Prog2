package de.hsa.games.fatsquirrel.fx;

import java.util.LinkedList;
import java.util.List;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;

public class GameImplFX extends Game {

	private HandOperatedMasterSquirrel masterSquirrel;
	private List<MasterSquirrel> squirrels = new LinkedList<>();

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
