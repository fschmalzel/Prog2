package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.OutOfViewException;
import de.hsa.games.fatsquirrel.botapi.SpawnException;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.util.XY;

public class MasterSquirrelBot extends MasterSquirrel {

	private BotControllerFactory factory;
	private BotController controller;
	private int VISIBILITY = 31;
	
	protected MasterSquirrelBot(XY xy, BotController contoller, BotControllerFactory factory) {
		super(xy);
		this.controller = contoller;
		this.factory = factory;
	}
	
	private class ControllerContextImpl implements ControllerContext {
		
		private EntityContext context;
		private boolean commandExecuted = false;
		
		private ControllerContextImpl(EntityContext context) {
			this.context = context;
		}
		
		@Override
		public XY getViewLowerLeft() {
			int y = getXY().y + (VISIBILITY - 1) / 2;

			int x = getXY().x - (VISIBILITY - 1) / 2;

			if (x < 0)
				x = 0;

			if (y > context.getSize().y)
				y = context.getSize().y;

			return new XY(x, y);
		}

		@Override
		public XY getViewUpperRight() {
			int x = getXY().x + (VISIBILITY - 1) / 2;

			int y = getXY().y - (VISIBILITY - 1) / 2;

			if (y < 0)
				y = 0;

			if (x > context.getSize().x)
				x = context.getSize().x;

			return new XY(x, y);
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			if (!isInView(xy))
				throw new OutOfViewException();

			return context.getEntityType(xy);

		}
		
		private boolean isInView(XY xy) {

			if (Math.abs(getXY().x - xy.x) > (VISIBILITY - 1) / 2)
				return false;
			else if (Math.abs(getXY().y - xy.y) > (VISIBILITY - 1) / 2)
				return false;

			return true;

		}

		@Override
		public void move(XY direction) {
			if (commandExecuted)
				return;
			context.tryMove(MasterSquirrelBot.this, direction);
			commandExecuted = true;
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			if (commandExecuted)
				return;
			
			if (energy > getEnergy())
				energy = getEnergy();
			
			MiniSquirrelBot s = new MiniSquirrelBot(energy, getXY().plus(direction), getID(), factory.createMiniBotController());
			if (context.tryInsert(s)) {
				updateEnergy(-energy);
				commandExecuted = true;
			} else {
				throw new SpawnException();
			}
		}

		@Override
		public int getEnergy() {
			return MasterSquirrelBot.this.getEnergy();
		}

		@Override
		public XY locate() {
			return getXY();
		}

		@Override
		public boolean isMine(XY xy) {
			return isChild(context.getEntity(xy));
		}

		@Override
		public void implode(int impactRadius) {}

		@Override
		public XY directionOfMaster() {
			return XY.ZERO_ZERO;
		}

		@Override
		public long getRemainingSteps() {
			// TODO getRemainingSteps
			return 0;
		}
		
	}
	
	public void nextStep(EntityContext context) {
		super.nextStep(context);
		if (isStunned())
			return;
		
		controller.nextStep(new ControllerContextImpl(context));
		
	}
}
