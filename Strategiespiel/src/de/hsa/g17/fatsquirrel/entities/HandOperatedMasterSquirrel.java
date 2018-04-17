package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.EntityContext;
import de.hsa.g17.fatsquirrel.core.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

	public HandOperatedMasterSquirrel(XY xy) {
		super(xy);
	}

	@Override
	public void nextStep(EntityContext context) {
		super.nextStep(context);
		if (isStunned())
			return;
		
		context.tryMove(this, context.getCommand().getMoveDirection());
		
	}

	public String toString() {
		return "HandOperatedMasterSquirrel" + super.toString();
	}
	
}
