package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.core.ui.console.ConsoleUI;
import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;
import de.hsa.g17.fatsquirrel.entities.MiniSquirrel;

public class GameImpl extends Game {
	
	private MasterSquirrel masterSquirrel;
	
	public GameImpl() {
		
		super(new State(),new ConsoleUI());	
		masterSquirrel = new HandOperatedMasterSquirrel(
				XY.getRandomCoordinates(state.getBoard().getConfig().getSize(), state.getBoard().getEntitys()));
		state.insertMaster(masterSquirrel);
	}
	
	@Override
	protected void processInput() {
		GameCommand cmd;
		
		do {
			
			cmd = ui.getCommand();
			
			switch (cmd.getType()) {
			case MASTERENERGY:
				System.out.println(masterSquirrel.getEnergy());
				break;
			case MOVE:
				masterSquirrel.setMoveCommand((MoveCommand) cmd);
				return;
			case SPAWN:
				SpawnCommand sCmd = (SpawnCommand) cmd;
				if (sCmd.getEnergy() > masterSquirrel.getEnergy()) {
					System.out.println("Not enough energy!");
					break;
				}
				
				XY xy = sCmd.getSpawnXY().add(masterSquirrel.getXY());
				if (xy.distance(masterSquirrel.getXY()) >= 2 || xy.equals(masterSquirrel.getXY())) {
					System.out.println("Invalid location!");
					break;
				}
				
				MiniSquirrel s = new MiniSquirrel(sCmd.getEnergy(), xy, masterSquirrel.getID());
				if (!state.getBoard().flatten().tryInsert(s)) {
					System.out.println("Invalid location!");
					break;
				} else {
					masterSquirrel.updateEnergy(-sCmd.getEnergy());
				}
				return;
			default:
				break;
			}
		} while (true);
		
	}

	@Override
	protected void render() {
		ui.render(
				state.flattenedBoard());
	}

}
