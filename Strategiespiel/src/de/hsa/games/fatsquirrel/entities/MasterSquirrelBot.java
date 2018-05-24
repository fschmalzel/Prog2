package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.OutOfViewException;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.core.SpawnCommand;
import de.hsa.games.fatsquirrel.util.XY;

public class MasterSquirrelBot extends MasterSquirrel {

	private BotControllerFactory factory;
	private BotController controller;
	private GameCommand command;
	
	protected MasterSquirrelBot(XY xy, BotController contoller, BotControllerFactory factory) {
		super(xy);
		this.controller = contoller;
		this.factory = factory;
	}
	
	private class ControllerContextImpl implements ControllerContext {
		
		private EntityContext context;
		
		private ControllerContextImpl(EntityContext context) {
			this.context = context;
		}
		
		@Override
		public XY getViewLowerLeft() {
			return getXY().plus(new XY(-6,6));
		}

		@Override
		public XY getViewUpperRight() {
			return getXY().plus(new XY(6,-6));
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			int x = Math.abs(getXY().x - xy.x);
			int y = Math.abs(getXY().y - xy.y);
			
			if (x <= 6 && y <= 6)
				return context.getEntityType(xy);
			else
				throw new OutOfViewException();
			
		}

		@Override
		public void move(XY direction) {
			command = new MoveCommand(direction);
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			//TODO delete buffer, create flag
			command = new SpawnCommand(energy, direction);
		}

		@Override
		public int getEnergy() {
			return MasterSquirrelBot.this.getEnergy();
		}

		@Override
		public XY locate() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isMine(XY xy) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void implode(int impactRadius) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public XY directionOfMaster() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getRemainingSteps() {
			// TODO Auto-generated method stub
			return 0;
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
		} else if (command instanceof SpawnCommand) {
			SpawnCommand sC = (SpawnCommand) command;
			MiniSquirrelBot s = new MiniSquirrelBot(sC.getEnergy(), sC.getSpawnXY().plus(getXY()), getID(), factory.createMiniBotController());
			if (context.tryInsert(s)) {
				updateEnergy(-sC.getEnergy());
			}
		}
		command = null;
		
	}
}