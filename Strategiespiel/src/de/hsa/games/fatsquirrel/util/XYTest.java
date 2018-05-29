package de.hsa.games.fatsquirrel.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class XYTest {


	private final XY xy1 = new XY(1, 3);
	private final XY xy2 = new XY(3, -2);
	
	@Test
	void testEqualsXY() {
		assertTrue(xy1.equals(new XY(1, 3)));
		assertFalse(xy1.equals(xy2));
	}
	
	@Test
	void testPlus() {
		assertEquals(new XY(4, 1), xy1.plus(xy2));
	}

	@Test
	void testMinus() {
		assertEquals(new XY(-2, 5), xy1.minus(xy2));
	}

	@Test
	void testTimes() {
		assertEquals(new XY(9, -6), xy2.times(3));
	}

	@Test
	void testLength() {
		assertEquals(Math.sqrt(3*3 + 2*2), xy2.length());
	}

	@Test
	void testDistanceFrom() {
		double distance = Math.sqrt(2*2 + 5*5);
		assertEquals(distance, xy2.distanceFrom(xy1));
		assertEquals(distance, xy1.distanceFrom(xy2));
	}

	

}
