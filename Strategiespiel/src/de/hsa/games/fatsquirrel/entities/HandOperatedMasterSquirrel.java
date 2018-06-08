package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.core.SpawnCommand;
import de.hsa.games.fatsquirrel.util.XY;

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
			
			XY xy = sCmd.getSpawnXY().plus(getXY());
			if (xy.distanceFrom(getXY()) >= 2 || xy.equals(getXY())) {
				break;
			}
			
			MiniSquirrel s = new HandOperatedMiniSquirrel(sCmd.getEnergy(), xy, this);
			if (context.tryInsert(s)) {
				updateEnergy(-sCmd.getEnergy());
			}
			break;
		default:
			break;
		}
		
		gameCommand = null;
		
	}

	public void setCommand(GameCommand gameCommand) {
		this.gameCommand = gameCommand;
	}
	
	public String toString() {
		return "HandOperatedMasterSquirrel" + super.toString();
	}
	
}
