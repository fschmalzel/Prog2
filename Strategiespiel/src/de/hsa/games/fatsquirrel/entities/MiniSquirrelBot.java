package de.hsa.games.fatsquirrel.entities;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.OutOfViewException;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotController controller;
	private static final int VISIBILITY = 21;

	protected MiniSquirrelBot(int energy, XY xy, MasterSquirrel master, BotController contoller) {
		super(energy, xy, master);
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
			if (!XYsupport.isInView(getXY(),xy,VISIBILITY))
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
			
			if (hasSameMaster(e)) {
				return true;
			} else if (e instanceof MasterSquirrel && ((MasterSquirrel) e).isChild(MiniSquirrelBot.this)) {
				return true;
			}
			return false;
		}

		@Override
		public void implode(int impactRadius) {
			context.implode(MiniSquirrelBot.this, impactRadius);
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
		
		ControllerContext view = new ControllerContextImpl(context);
		
		final Logger logger = Logger.getLogger(Launcher.class.getName());
		
		InvocationHandler handler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				logger.finer("MiniSquirrelBot with id " + getID() + " invoked method " + method.getName() + "!");
				
				return method.invoke(view, args);
			}
		};
		
		ControllerContext proxy = (ControllerContext) Proxy.newProxyInstance(ControllerContext.class.getClassLoader(),
				new Class<?>[] {ControllerContext.class}, handler);
		
		controller.nextStep(proxy);

	}
}