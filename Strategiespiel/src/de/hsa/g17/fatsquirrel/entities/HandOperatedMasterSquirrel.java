package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.EntityContext;
import de.hsa.g17.fatsquirrel.core.GameCommand;
import de.hsa.g17.fatsquirrel.core.MoveCommand;
import de.hsa.g17.fatsquirrel.core.SpawnCommand;
import de.hsa.g17.fatsquirrel.core.UI;
import de.hsa.g17.fatsquirrel.core.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
	
	private GameCommand gameCommand;
	
	public HandOperatedMasterSquirrel(XY xy) {
		super(xy);
	}

	@Override
	public void nextStep(EntityContext context) {
		super.nextStep(context);
		if (isStunned())
			return;
		
		if (gameCommand == null)
			return;
		
		switch(gameCommand.getType()) {
		case MOVE:
			context.tryMove(this, ((MoveCommand) gameCommand).getMoveDirection());
			break;
		case SPAWN:
			SpawnCommand sCmd = (SpawnCommand) gameCommand;
			if (sCmd.getEnergy() > getEnergy()) {
				break;
			}
			
			XY xy = sCmd.getSpawnXY().add(getXY());
			if (xy.distance(getXY()) >= 2 || xy.equals(getXY())) {
				break;
			}
			
			MiniSquirrel s = new MiniSquirrel(sCmd.getEnergy(), xy, getID());
			if (context.tryInsert(s)) {
				updateEnergy(-sCmd.getEnergy());
			}
			break;
		default:
			break;
		}
		
	}

	public void setCommand(GameCommand gameCommand) {
		this.gameCommand = gameCommand;
	}
	
	public String toString() {
		return "HandOperatedMasterSquirrel" + super.toString();
	}
	
}
