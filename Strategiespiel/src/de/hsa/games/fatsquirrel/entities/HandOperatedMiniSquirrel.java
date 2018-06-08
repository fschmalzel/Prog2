package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.util.XY;

public class HandOperatedMiniSquirrel extends MiniSquirrel {

	private MoveCommand moveCommand;

	public HandOperatedMiniSquirrel(int energy, XY xy, MasterSquirrel master) {
		super(energy, xy, master);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void nextStep(EntityContext context) {
		super.nextStep(context);
		
		if (isStunned())
			return;
		
		if(moveCommand == null)
			return;
		else
			context.tryMove(this, moveCommand.getMoveDirection());
		moveCommand = null;
	}

	public void setMoveCommand(MoveCommand moveCommand) {
		this.moveCommand = moveCommand;
	}

}
