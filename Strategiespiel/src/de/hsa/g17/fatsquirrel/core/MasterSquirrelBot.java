package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.botapi.ControllerContext;
import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;

public class MasterSquirrelBot extends MasterSquirrel{

	protected MasterSquirrelBot(XY xy) {
		super(xy);
	}
	
	public void nextStep() {
		ControllerContextImpl controller = new ControllerContextImpl();
	}
}
