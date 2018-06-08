package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Squirrel;
import de.hsa.games.fatsquirrel.util.XY;

class GoodBeastTest {

	private class EntityContextMock implements EntityContext {

		public boolean moved = false;
		public XY moveDirection = null;
		public XY xy = null;
		public Squirrel nearestSquirrel = null;

		@Override
		public XY getSize() {
			return new XY(10, 10);
		}

		@Override
		public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {}

		@Override
		public void tryMove(GoodBeast goodBeast, XY moveDirection) {
			moved = true;
			this.moveDirection = moveDirection;
		}

		@Override
		public void tryMove(BadBeast badBeast, XY moveDirection) {}

		@Override
		public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {}

		@Override
		public Squirrel nearestSquirrel(XY xy) {
			this.xy = xy;
			return nearestSquirrel;
		}

		@Override
		public void kill(Entity entity) {}

		@Override
		public void killAndReplace(Entity entity) {}

		@Override
		public EntityType getEntityType(XY xy) { return EntityType.NONE; }

		@Override
		public boolean tryInsert(Entity s) { return false; }

		@Override
		public Entity getEntity(XY xy) { return null; }

		@Override
		public MasterSquirrel getMaster(MiniSquirrel s) { return null; }
		
		@Override
		public void implode(MiniSquirrel m, int impactRadius) {}
		
	}
	
	
	@Test
	void testSimpleMove() {
		EntityContextMock con = new EntityContextMock();
		GoodBeast goodBeast = new GoodBeast(new XY(1, 2));

		goodBeast.nextStep(con);
		assertTrue(con.moved);
	}
	
	@Test
	void testEvery4Steps() {
		EntityContextMock con = new EntityContextMock();
		
		GoodBeast goodBeast = new GoodBeast(new XY(3, 3));
		
		goodBeast.nextStep(con);
		
		assertTrue(con.moved);
		assertTrue(con.moveDirection != null && con.moveDirection.length() < 2);
		con.moved = false;
		con.moveDirection = null;
		
		for (int i = 0; i < 3; i++)
			goodBeast.nextStep(con);

		assertFalse(con.moved);
		assertTrue(con.moveDirection == null);
		
		goodBeast.nextStep(con);
		
		assertTrue(con.moved);
		assertTrue(con.moveDirection != null && con.moveDirection.length() < 2);
	}
	
	@Test
	void testRunAwayFromSquirrel() {
		EntityContextMock con = new EntityContextMock();
		con.nearestSquirrel = new HandOperatedMasterSquirrel(new XY(5,5));
		
		GoodBeast goodBeast = new GoodBeast(new XY(3, 3));

		XY xy = goodBeast.getXY();
		
		goodBeast.nextStep(con);

		assertEquals(xy, con.xy);
		assertTrue(con.moved);
		assertTrue(con.moveDirection != null && con.moveDirection.length() < 2);
		assertEquals(new XY(-1, -1), con.moveDirection);
	}
	
}


















