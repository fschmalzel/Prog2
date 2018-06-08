package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;

public class CollisionsBadBeastTest {
	
	BoardConfig cfg;
	
	@BeforeEach
	void setUp() {
		cfg = mock(BoardConfig.class);
		when(cfg.getSize()).thenReturn(new XY(20, 20));
	}
	
	@Test
	void testTryMoveBadBeast() {
		BadBeast badBeast = new BadBeast(new XY(5, 5));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {badBeast});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(badBeast, new XY(1,1));
		assertEquals(new XY(6,6), badBeast.getXY());
		assertEquals(-150, badBeast.getEnergy());
		verify(board, never()).remove(badBeast);
	}

	@Test
	void testCollisionWall() {
		BadBeast badBeast = new BadBeast(new XY(5, 5));
		Wall wall = new Wall(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {badBeast, wall});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(badBeast, new XY(1,1));
		assertEquals(new XY(5,5), badBeast.getXY());
		assertEquals(-150, badBeast.getEnergy());
		verify(board, never()).remove(any());
	}
	
	@Test
	void testCollisionSquirrel() {
		BadBeast badBeast = new BadBeast(new XY(5, 5));
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {badBeast, masterSquirrel});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(badBeast, new XY(1,1));
		verify(board, never()).remove(badBeast);
		assertEquals(850, masterSquirrel.getEnergy());
	}
	
	@Test
	void testDeathAfter7Bites() {
		BadBeast badBeast = new BadBeast(new XY(5, 5));
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {badBeast, masterSquirrel});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		for (int i = 1; i <= 6; i++)
			flatBoard.tryMove(badBeast, new XY(1,1));
		verify(board, never()).remove(badBeast);
		
		flatBoard.tryMove(badBeast, new XY(1,1));
		verify(board).remove(badBeast);
	}
	
}
