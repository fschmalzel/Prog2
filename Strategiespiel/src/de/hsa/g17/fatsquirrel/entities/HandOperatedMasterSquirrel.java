package de.hsa.g17.fatsquirrel.entities;

import de.hsa.g17.fatsquirrel.core.EntityContext;
import de.hsa.g17.fatsquirrel.core.UI;
import de.hsa.g17.fatsquirrel.core.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
	
	public HandOperatedMasterSquirrel(XY xy, UI ui) {
		super(xy);
	}

	@Override
	public void nextStep(EntityContext context) {
		System.out.println(this);
		super.nextStep(context);
		if (isStunned())
			return;
		
		context.tryMove(this, moveCommand.getMoveDirection());
		
	}

	public String toString() {
		return "HandOperatedMasterSquirrel" + super.toString();
	}
	
}
