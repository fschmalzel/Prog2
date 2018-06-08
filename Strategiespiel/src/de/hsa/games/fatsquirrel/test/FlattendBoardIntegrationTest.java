package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.util.XY;

class FlattendBoardIntegrationTest {
	
	Board board;
	FlattenedBoard flatBoard;
	
	@BeforeEach
	void setUp() {
		BoardConfig cfg = mock(BoardConfig.class);
		when(cfg.getNumBadBeast()).thenReturn(0);
		when(cfg.getNumBadPlant()).thenReturn(0);
		when(cfg.getNumGoodBeast()).thenReturn(0);
		when(cfg.getNumGoodPlant()).thenReturn(0);
		when(cfg.getNumWall()).thenReturn(0);
		when(cfg.getBots()).thenReturn(new LinkedList<Class<? extends BotControllerFactory>>());
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		
		board = new Board(cfg);
		flatBoard = board.flatten();
	}

	//TODO Implement everything
	//board is new for every function
	//there are just walls in the board
	
	@Test
	void testNearestSquirrel() {
		fail("Not yet implemented");
	}

	@Test
	void testTryInsert() {
		fail("Not yet implemented");
	}

	@Test
	void testImplode() {
		fail("Not yet implemented");
	}

	@Test
	void testKill() {
		fail("Not yet implemented");
	}

	@Test
	void testKillAndReplace() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEntityType() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEntity() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSize() {
		fail("Not yet implemented");
	}

}
