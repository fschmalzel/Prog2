package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.util.XY;

public class InvocationHandler implements ControllerContext {

	private ControllerContext cc;
	
	public InvocationHandler(ControllerContext cc) {
		this.cc = cc;
	}

	@Override
	public XY getViewLowerLeft() {
		return cc.getViewLowerLeft();
	}

	@Override
	public XY getViewUpperRight() {
		return cc.getViewUpperRight();
	}

	@Override
	public XY locate() {
		return cc.locate();
	}

	@Override
	public EntityType getEntityAt(XY xy) {
		return cc.getEntityAt(xy);
	}

	@Override
	public boolean isMine(XY xy) {
		return cc.isMine(xy);
	}

	@Override
	public void move(XY direction) {
		cc.move(direction);
	}

	@Override
	public void spawnMiniBot(XY direction, int energy) {
		cc.spawnMiniBot(direction, energy);
	}

	@Override
	public void implode(int impactRadius) {
		cc.implode(impactRadius);
	}

	@Override
	public int getEnergy() {
		return cc.getEnergy();
	}

	@Override
	public XY directionOfMaster() {
		return cc.directionOfMaster();
	}

	@Override
	public long getRemainingSteps() {
		return cc.getRemainingSteps();
	}

}
