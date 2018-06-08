package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;

public class CollisionsGoodBeastTest {
	
	BoardConfig cfg;
	
	@BeforeEach
	void setUp() {
		cfg = mock(BoardConfig.class);
		when(cfg.getSize()).thenReturn(new XY(20, 20));
	}
	
	@Test
	void testTryMoveGoodBeast() {
		GoodBeast goodBeast = new GoodBeast(new XY(5, 5));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {goodBeast});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(goodBeast, new XY(1,1));
		assertEquals(new XY(6,6), goodBeast.getXY());
		assertEquals(200, goodBeast.getEnergy());
		verify(board, never()).remove(goodBeast);
	}

	@Test
	void testCollisionWall() {
		GoodBeast goodBeast = new GoodBeast(new XY(5, 5));
		Wall wall = new Wall(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {goodBeast, wall});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(goodBeast, new XY(1,1));
		assertEquals(new XY(5,5), goodBeast.getXY());
		assertEquals(200, goodBeast.getEnergy());
		verify(board, never()).remove(any());
	}
	
	@Test
	void testCollisionSquirrel() {
		GoodBeast goodBeast = new GoodBeast(new XY(5, 5));
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {goodBeast, masterSquirrel});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(goodBeast, new XY(1,1));
		verify(board).remove(goodBeast);
		assertEquals(1200, masterSquirrel.getEnergy());
	}
	
}
