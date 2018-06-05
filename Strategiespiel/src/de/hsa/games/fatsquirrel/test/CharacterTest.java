package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Squirrel;
import de.hsa.games.fatsquirrel.util.XY;

class CharacterTest {

	private class EntityContextImpl implements EntityContext {

		public boolean moved = false;
		public Object arg = null;

		@Override
		public XY getSize() {
			return new XY(5,6);
		}

		@Override
		public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
			moved = true;
			arg = moveDirection;
		}

		@Override
		public void tryMove(GoodBeast goodBeast, XY moveDirection) {
			moved = true;
			arg = moveDirection;

		}

		@Override
		public void tryMove(BadBeast badBeast, XY moveDirection) {
			moved = true;
			arg = moveDirection;

		}

		@Override
		public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
			moved = true;
			arg = moveDirection;

		}

		@Override
		public Squirrel nearestSquirrel(XY pos) {
			arg = pos;
			return null;
		}

		@Override
		public void kill(Entity entity) {
			arg = entity;
		}

		@Override
		public void killAndReplace(Entity entity) {
			arg = entity;
		}

		@Override
		public EntityType getEntityType(XY xy) {
			arg = xy;
			return null;
		}

		@Override
		public boolean tryInsert(Entity s) {
			arg = s;
			return false;
		}

		@Override
		public Entity getEntity(XY xy) {
			arg = xy;
			return null;
		}

		@Override
		public MasterSquirrel getMaster(MiniSquirrel s) {
			arg = s;
			return null;
		}

		@Override
		public void implode(MiniSquirrel m, int impactRadius) {
			arg = m;		}

	}

	@Test
	void testNextStepBadBeast() {
		EntityContextImpl con = new EntityContextImpl();
		BadBeast badBeast = new BadBeast(new XY(1, 2));

		badBeast.nextStep(con);

		assertTrue(con.moved);
		con.moved = false;
		
		if(con.arg instanceof XY) {
			assertTrue(((XY) con.arg).length() < 2);
			for (int i = 0; i < 3; i++)
				badBeast.nextStep(con);
			
			assertFalse(con.moved);
			
			badBeast.nextStep(con);
			assertTrue(con.moved);
		}else
			fail("args aren't a instance of XY!!!!");
		
	}

	@Test
	void testNextStepMasterSquirrel() {
		EntityContextImpl con = new EntityContextImpl();
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(5,6));
		master.setCommand(new MoveCommand(new XY(1,1)));
		master.nextStep(con);
		
		if(con.arg instanceof XY) {
			assertTrue(((XY) con.arg).length() < 2);
			master.stun();
			
			for(int i = 0; i < 3; i++) {
				assertTrue(master.isStunned());
				master.nextStep(con);
			}
			
			assertFalse(master.isStunned());
		}else
			fail("args aren't a instance of XY!!!!");
	}

	@Test
	void testNextStepMiniSquirrel() {
		EntityContextImpl con = new EntityContextImpl();
		MiniSquirrel mini = new MiniSquirrel(100, new XY(5,6), new HandOperatedMasterSquirrel(new XY(2,3)));

		mini.setMoveCommand(new MoveCommand(new XY(1,1)));
		mini.nextStep(con);
		
		if(con.arg instanceof XY) {
			assertTrue(((XY) con.arg).length() < 2);
		}else
			fail("args aren't a instance of XY!!!!");
	}
	
	@Test
	void testNextStepGoodBeast() {
		EntityContextImpl con = new EntityContextImpl();
		GoodBeast goodBeast = new GoodBeast(new XY(1, 2));

		goodBeast.nextStep(con);

		assertTrue(con.moved);
		con.moved = false;
		
		if(con.arg instanceof XY) {
			assertTrue(((XY) con.arg).length() < 2);
			for (int i = 0; i < 3; i++)
				goodBeast.nextStep(con);
			
			assertFalse(con.moved);
			
			goodBeast.nextStep(con);
			assertTrue(con.moved);
		}else
			fail("args aren't a instance of XY!!!!");
		
	}
}
