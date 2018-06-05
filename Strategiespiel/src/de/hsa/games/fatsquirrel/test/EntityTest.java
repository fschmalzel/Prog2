package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.util.XY;

class EntityTest {

	private final BadBeast bb1 = new BadBeast(new XY(2,3));
	private final BadBeast bb2 = new BadBeast(new XY(4,7));
	private final GoodBeast gb1 = new GoodBeast(new XY(7,2));
	
	
	@Test
	void testGetEnergy() {
		assertEquals(-150, bb1.getEnergy());
	}

	@Test
	void testUpdateEnergy() {
		bb1.updateEnergy(100);
		assertEquals(-50, bb1.getEnergy());
	}

	@Test
	void testGetXY() {
		assertEquals(new XY(2,3), bb1.getXY());
	}

	@Test
	void testMove() {
		BadBeast bb3 = new BadBeast(new XY(2, 3));
		bb3.move(new XY(1,1));
		assertEquals(new XY(3,4), bb3.getXY());
	}

	@Test
	void testGetID() {
		final BadBeast bb3 = new BadBeast(new XY(1, 1));
		final BadBeast bb4 = new BadBeast(new XY(1, 3));
		assertEquals(bb3.getID()+1, bb4.getID());
		assertFalse(bb3.getID() == bb4.getID());
	}

	@Test
	void testEquals() {
		assertTrue(bb1.equals(bb1));
		assertFalse(bb1.equals(bb2));
	}

	@Test
	void testIsSameType() {
		assertTrue(bb1.isSameType(bb2));
		assertFalse(bb1.isSameType(gb1));
	}

	@Test
	void testGetEntityType() {
		assertTrue(bb1.getEntityType() == EntityType.BAD_BEAST);
	}

}
