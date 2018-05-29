package de.hsa.games.fatsquirrel.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.channels.AsynchronousServerSocketChannel;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.util.XY;

class BadBeastTest {

	private final BadBeast bb1 = new BadBeast(new XY(2,3));
	private final BadBeast bb2 = new BadBeast(new XY(4,7));
	private final GoodBeast db1 = new GoodBeast(new XY(7,2));
	
	
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
		bb1.move(new XY(1,1));
		assertEquals(new XY(3,4), bb1.getXY());
	}

	@Test
	void testGetID() {
		assertTrue(0 == bb1.getID());
		assertFalse(1 == bb1.getID());
	}

	@Test
	void testEquals() {
		assertTrue(bb1.equals(bb1));
		assertFalse(bb1.equals(bb2));
	}

	@Test
	void testIsSameType() {
		assertTrue(bb1.isSameType(bb2));
		assertFalse(bb1.isSameType(db1));
	}

	@Test
	void testGetEntityType() {
		assertTrue(bb1.getEntityType() == EntityType.BAD_BEAST);
	}
	
	@Test
	void testNextStep() {
		for(int i = 0; i < 4; i++)
			bb1.nextStep(context);
		
	}

}
