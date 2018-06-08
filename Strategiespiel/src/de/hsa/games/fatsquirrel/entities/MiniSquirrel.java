package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

public abstract class MiniSquirrel extends Squirrel {
	
	private final MasterSquirrel master;
	
	public MiniSquirrel(int energy, XY xy, MasterSquirrel master) {
		super(energy, xy);
		this.master = master;
	}

	public MasterSquirrel getMaster() {
		return master;
	}
	
	public boolean hasSameMaster(Entity e) {
		if (e == null || !(e instanceof MiniSquirrel))
			return false;
		return master.equals(((MiniSquirrel) e).getMaster());
	}
	
	public String toString() {
		return "MiniSquirrel" + super.toString();
	}

	@Override
	public void nextStep(EntityContext context) {
		super.nextStep(context);
		if (isStunned())
			return;
		
		updateEnergy(-1);
		if(getEnergy() <= 0)
			context.kill(this);
	}
	
}
