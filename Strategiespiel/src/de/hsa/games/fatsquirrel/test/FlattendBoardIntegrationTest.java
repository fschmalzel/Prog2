package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.HandOperatedMiniSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Squirrel;
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
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(7,7));
		MiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		
		board.insert(masterSquirrel);
		board.insert(miniSquirrel);
		FlattenedBoard flatBoard = new FlattenedBoard(board);

		Squirrel nearestSquirrel = flatBoard.nearestSquirrel(new XY(3, 3));
		
		assertEquals(miniSquirrel, nearestSquirrel);
	}

	@Test
	void testTryInsert() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(7,7));
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertTrue(flatBoard.tryInsert(masterSquirrel));
		assertFalse(flatBoard.tryInsert(goodBeast));
	}

	@Test
	void testImplode() {
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(3,3));
		HandOperatedMiniSquirrel mini = new HandOperatedMiniSquirrel(100, new XY(2,2), master);
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(master.getEnergy(), 1000);
		
		flatBoard.implode(mini, 2);
		
		assertTrue(master.getEnergy() == 1000);
	}

	@Test
	void testKill() {
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));
		
//		board.insert(goodBeast);
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.kill(goodBeast);
	}

	@Test
	void testKillAndReplace() {
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));
		
//		board.insert(goodBeast);
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.killAndReplace(goodBeast);
	}

	@Test
	void testGetEntityType() {
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));
		
//		board.insert(goodBeast);
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(EntityType.GOOD_BEAST, flatBoard.getEntityType(goodBeast.getXY()));
		assertEquals(EntityType.NONE, flatBoard.getEntityType(new XY(2,2)));
	}

	@Test
	void testGetEntity() {
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));
		
		board.insert(goodBeast);
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(goodBeast, flatBoard.getEntity(goodBeast.getXY()));
	}

	@Test
	void testGetSize() {
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(new XY(20,20),flatBoard.getSize());
	}

}
