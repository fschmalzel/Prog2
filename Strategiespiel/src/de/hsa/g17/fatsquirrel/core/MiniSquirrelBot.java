package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.botapi.BotController;
import de.hsa.g17.fatsquirrel.botapi.ControllerContext;
import de.hsa.g17.fatsquirrel.entities.MiniSquirrel;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotController controller;
	private GameCommand command;
	
	protected MiniSquirrelBot(int energy, XY xy, int masterID, BotController contoller) {
		super(energy, xy, masterID);
		this.controller = contoller;
	}
	
	private class ControllerContextImpl implements ControllerContext {
		
		private EntityContext context;
		
		private ControllerContextImpl(EntityContext context) {
			this.context = context;
		}
		
		@Override
		public XY getViewLowerLeft() {
			return getXY().add(new XY(-6,6));
		}

		@Override
		public XY getViewUpperRight() {
			return getXY().add(new XY(6,-6));
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			int x = Math.abs(getXY().x() - xy.x());
			int y = Math.abs(getXY().y() - xy.y());
			
			if (x <= 6 && y <= 6)
				return context.getEntityType(xy);
			else
				return null;
			
		}

		@Override
		public void move(XY direction) {
			command = new MoveCommand(direction);
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {}

		@Override
		public int getEnergy() {
			return MiniSquirrelBot.this.getEnergy();
		}
		
	}
	
	public void nextStep(EntityContext context) {
		super.nextStep(context);
		if (isStunned())
			return;
		
		controller.nextStep(new ControllerContextImpl(context));
		
		if (command == null)
			return;
		
		if (command instanceof MoveCommand) {
			context.tryMove(this, ((MoveCommand) command).getMoveDirection());
		}
		
		command = null;
		
	}
}