package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

public abstract class Character extends Entity {

	public Character(int energy, XY xy) {
		super(energy, xy);
	}
	
	abstract public void nextStep(EntityContext context);

}
