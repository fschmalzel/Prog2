package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.HandOperatedMiniSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Squirrel;
import de.hsa.games.fatsquirrel.util.XY;

class FlattenedBoardTest {
	
	BoardConfig cfg;
	
	@BeforeEach
	void setUp() {
		cfg = mock(BoardConfig.class);
		when(cfg.getSize()).thenReturn(new XY(20, 20));
	}

	@Test
	void testNearestSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(7,7));
		MiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);

		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, miniSquirrel});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);

		Squirrel nearestSquirrel = flatBoard.nearestSquirrel(new XY(3, 3));
		
		assertEquals(miniSquirrel, nearestSquirrel);
	}

	@Test
	void testTryInsert() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(7,7));
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertTrue(flatBoard.tryInsert(masterSquirrel));
		verify(board).insert(masterSquirrel);
		
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel});
		
		assertFalse(flatBoard.tryInsert(goodBeast));
		verify(board, never()).insert(goodBeast);
	}

	@Test
	void testKill() {
		
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));


		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {goodBeast});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.kill(goodBeast);
		verify(board).remove(goodBeast);

	}

	@Test
	void testKillAndReplace() {
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {goodBeast});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.killAndReplace(goodBeast);
		verify(board).remove(goodBeast);
		verify(board).insert(any(GoodBeast.class));
	}
		

	@Test
	void testGetEntityType() {
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {goodBeast});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(EntityType.GOOD_BEAST, flatBoard.getEntityType(goodBeast.getXY()));
		assertEquals(EntityType.NONE, flatBoard.getEntityType(new XY(2,2)));
	}

	@Test
	void testGetEntity() {
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] { goodBeast });
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(goodBeast, flatBoard.getEntity(goodBeast.getXY()));
	}

	@Test
	void testGetSize() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(new XY(20,20),flatBoard.getSize());
	}
	
	@Test
	void testImplode() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(3,3));
		HandOperatedMiniSquirrel mini = new HandOperatedMiniSquirrel(100, new XY(2,2), master);
		
		assertEquals(master.getEnergy(), 1000);
		
		flatBoard.implode(mini, 2);
		
		verify(board).remove(mini);
		
		assertTrue(master.getEnergy() == 1000);
		
		
	}

}
