package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.InvocationHandler;
import de.hsa.games.fatsquirrel.botapi.OutOfViewException;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.util.XY;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotController controller;
	private static final int VISIBILITY = 21;

	protected MiniSquirrelBot(int energy, XY xy, int masterID, BotController contoller) {
		super(energy, xy, masterID);
		this.controller = contoller;
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

		@Override
		public void move(XY direction) {
			if (commandExecuted)
				return;
			context.tryMove(MiniSquirrelBot.this, direction);
			commandExecuted = true;
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
		}

		@Override
		public int getEnergy() {
			return MiniSquirrelBot.this.getEnergy();
		}

		@Override
		public XY locate() {
			return getXY();
		}

		@Override
		public boolean isMine(XY xy) {
			Entity e = context.getEntity(xy);
			
			if (e instanceof MiniSquirrel && ((MiniSquirrel) e).getMasterID() == getMasterID()) {
				return true;
			} else if (e instanceof MasterSquirrel && e.getID() == getMasterID()) {
				return true;
			}
			return false;
		}

		@Override
		public void implode(int impactRadius) {
			if (!(impactRadius >= 2 && impactRadius <= 10))
				return;

			double impactArea = impactRadius * impactRadius * Math.PI;
			
			int collectedEnergy = 0;
			
			for (int i = -impactRadius; i < impactRadius; i++) {
				for (int j = -impactRadius; j < impactRadius; j++) {
					Entity e = context.getEntity(new XY(getXY().x + i, getXY().y + j));

					if (e instanceof MasterSquirrel && e.getID() == getMasterID())
						continue;
					else if (e instanceof MiniSquirrel && ((MiniSquirrel) e).getMasterID() == getMasterID())
						continue;
					else if (e instanceof Wall)
						continue;
					
					double distance = getXY().distanceFrom(e.getXY());
					int energyLoss = (int) (200 * (getEnergy() / impactArea) * (1 - distance / impactRadius));
					
					switch(e.getEntityType()) {
					case BAD_BEAST:
					case BAD_PLANT:
						e.updateEnergy(-energyLoss);
						if (e.getEnergy() >= 0)
							context.killAndReplace(e);
						break;
					case MASTER_SQUIRREL:
						if (e.getEnergy() < - energyLoss)
							energyLoss = -e.getEnergy();
						e.updateEnergy(energyLoss);
						break;
					case GOOD_PLANT:
					case GOOD_BEAST:
						e.updateEnergy(energyLoss);
						if (e.getEnergy() <= 0)
							context.killAndReplace(e);
						break;
					default:
						continue;
					}
					
					collectedEnergy -= energyLoss;
					
				}
			}
			
			context.getMaster(MiniSquirrelBot.this).updateEnergy(collectedEnergy);

		}

		private boolean isInView(XY xy) {

			if (Math.abs(getXY().x - xy.x) > (VISIBILITY - 1) / 2)
				return false;
			else if (Math.abs(getXY().y - xy.y) > (VISIBILITY - 1) / 2)
				return false;

			return true;

		}

		@Override
		public XY directionOfMaster() {
			XY xy = context.getMaster(MiniSquirrelBot.this).getXY();
			
			int x = 0;
			int y = 0;
			
			if( xy.x > 0)
				x = 1;
			else if ( xy.x < 0)
				x = -1;
			
			if( xy.y > 0)
				y = 1;
			else if ( xy.y < 0)
				y = -1;
			
			return new XY(x, y);
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

		controller.nextStep(new InvocationHandler(new ControllerContextImpl(context)));

	}
}