package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

class XYsupportTest {

	@Test
	void testGetRandomVector() {
		assertTrue(XYsupport.getRandomVector().length() < 2);
	}

	@Test
	void testGetRandomCoordinates() {
		Board board = new Board(new BoardConfig());
		
		XY xy = XYsupport.getRandomCoordinates(board);
		
		assertTrue(xy.x <= board.getConfig().getSize().x && xy.x >= 0);
		assertTrue(xy.y <= board.getConfig().getSize().y && xy.y >= 0);
		
	}

}
