package de.hsa.games.fatsquirrel.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.util.XY;

class CharacterTest {

	private class EntityContextImpl implements EntityContext {

		public boolean moved = false;
		public boolean removed = false; //probably useless !!!
		public boolean added = false;
		public boolean found = false;
		public Object args1 = null;
		public Object args2 = null;

		@Override
		public XY getSize() {
			return new XY(5,6);
		}

		@Override
		public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
			moved = true;
			args1 = moveDirection;
		}

		@Override
		public void tryMove(GoodBeast goodBeast, XY moveDirection) {
			moved = true;
			args1 = moveDirection;

		}

		@Override
		public void tryMove(BadBeast badBeast, XY moveDirection) {
			moved = true;
			args1 = moveDirection;

		}

		@Override
		public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
			moved = true;
			args1 = moveDirection;

		}

		@Override
		public Squirrel nearestSquirrel(XY pos) {
			found = true;
			args1 = pos;
			return null;
		}

		@Override
		public void kill(Entity entity) {
			removed = true;
			args1 = entity;
		}

		@Override
		public void killAndReplace(Entity entity) {
			removed = true;
			added = true;
			args1 = entity;
		}

		@Override
		public EntityType getEntityType(XY xy) {
			args1 = xy;
			return null;
		}

		@Override
		public boolean tryInsert(Entity s) {
			args1 = s;
			return false;
		}

		@Override
		public Entity getEntity(XY xy) {
			args1 = xy;
			return null;
		}

		@Override
		public MasterSquirrel getMaster(MiniSquirrel s) {
			args1 = s;
			return null;
		}

		@Override
		public void implode(MiniSquirrel m, int impactRadius) {
			args1 = m;
			args2 = impactRadius;
		}

	}

	@Test
	void testNextStepBadBeast() {
		EntityContextImpl con = new EntityContextImpl();
		BadBeast badBeast = new BadBeast(new XY(1, 2));
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(5,6));

		badBeast.nextStep(con);

		assertTrue(con.moved);
		con.moved = false;
		
		if(con.args1 instanceof XY) {
			assertTrue(((XY) con.args1).length() < 2);
			for (int i = 0; i < 3; i++)
				badBeast.nextStep(con);
			
			assertFalse(con.moved);
			
			badBeast.nextStep(con);
			assertTrue(con.moved);
		}else
			fail("args aren't a instance of XY!!!!");
		
		master.nextStep(con);
		
		if(con.args1 instanceof XY) {
			assertTrue(((XY) con.args1).length() < 2);
			master.stun();
			
			assertTrue(master.isStunned());
			
			for(int i = 0; i < 3; i++)
				master.nextStep(con);
			
			assertFalse(master.isStunned());
		}else
			fail("args aren't a instance of XY!!!!");
		
		
		
		
	}

}
