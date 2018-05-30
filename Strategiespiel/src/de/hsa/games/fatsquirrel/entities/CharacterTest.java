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
		public XY args = null;

		@Override
		public XY getSize() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {

		}

		@Override
		public void tryMove(GoodBeast goodBeast, XY moveDirection) {
			// TODO Auto-generated method stub

		}

		@Override
		public void tryMove(BadBeast badBeast, XY moveDirection) {
			moved = true;
			args = moveDirection;

		}

		@Override
		public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
			// TODO Auto-generated method stub

		}

		@Override
		public Squirrel nearestSquirrel(XY pos) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void kill(Entity entity) {
			// TODO Auto-generated method stub

		}

		@Override
		public void killAndReplace(Entity entity) {
		}

		@Override
		public EntityType getEntityType(XY xy) {
			return null;
		}

		@Override
		public boolean tryInsert(Entity s) {
			return false;
		}

		@Override
		public Entity getEntity(XY xy) {
			return null;
		}

		@Override
		public MasterSquirrel getMaster(MiniSquirrel s) {
			return null;
		}

		@Override
		public void implode(MiniSquirrel m, int impactRadius) {
			// TODO Auto-generated method stub

		}

	}

	@Test
	void testNextStepBadBeast() {
		EntityContextImpl con = new EntityContextImpl();
		BadBeast badBeast = new BadBeast(new XY(1, 2));

		badBeast.nextStep(con);

		assertTrue(con.moved);
		con.moved = false;
		assertTrue(con.args.length() < 2);
		for (int i = 0; i < 3; i++)
			badBeast.nextStep(con);

		assertFalse(con.moved);

		badBeast.nextStep(con);
		assertTrue(con.moved);
	}

}
