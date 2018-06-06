package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

class XYsupportTest {
	
	@Test
	void testGetRandomVector() {
		//TODO Streung testen
		assertTrue(XYsupport.getRandomVector().length() < 2);
	}

	@Test
	void testGetRandomCoordinates() {
		
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		when(board.getConfig()).thenReturn(cfg);
		when(cfg.getSize()).thenReturn(new XY(4,4));
		when(board.getEntitys()).thenReturn(new Entity[] {new Wall(new XY(1,1)),new Wall(new XY(1,2)),new Wall(new XY(2,1))});
		
		for (int i = 0; i < 10; i++) {
			XY xy = XYsupport.getRandomCoordinates(board);
			
			assertTrue(xy.x <= board.getConfig().getSize().x && xy.x >= 0);
			assertTrue(xy.y <= board.getConfig().getSize().y && xy.y >= 0);
			assertEquals(new XY(2,2), xy);
		}
		
	}

}
