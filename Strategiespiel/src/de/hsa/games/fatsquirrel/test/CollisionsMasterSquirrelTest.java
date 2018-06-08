package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.HandOperatedMiniSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;

public class CollisionsMasterSquirrelTest {
	
	BoardConfig cfg;
	
	@BeforeEach
	void setUp() {
		cfg = mock(BoardConfig.class);
		when(cfg.getSize()).thenReturn(new XY(20, 20));
	}

	@Test
	void testTryMoveMasterSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
	
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(masterSquirrel, new XY(1, 1));
		assertEquals(new XY(6, 6),  masterSquirrel.getXY());
		assertEquals(1000, masterSquirrel.getEnergy());
	
	}

	@Test
	void testCollisionWall() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
		Wall wall = new Wall(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, wall});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(masterSquirrel, new XY(1,1));
		assertEquals(new XY(5,5), masterSquirrel.getXY());
		assertEquals(990, masterSquirrel.getEnergy());
		verify(board, never()).remove(wall);
		assertTrue(masterSquirrel.isStunned());
	}

	@Test
	void testCollisionGoodPlant() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
		GoodPlant goodPlant = new GoodPlant(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, goodPlant});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(masterSquirrel, new XY(1,1));
		assertEquals(new XY(6,6), masterSquirrel.getXY());
		assertEquals(1100, masterSquirrel.getEnergy());
		verify(board).remove(goodPlant);
	}

	@Test
	void testCollisionBadPlant() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
		BadPlant badPlant = new BadPlant(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, badPlant});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(masterSquirrel, new XY(1,1));
		assertEquals(900, masterSquirrel.getEnergy());
		assertEquals(new XY(6,6), masterSquirrel.getXY());
		verify(board).remove(badPlant);
	}

	@Test
	void testCollisionGoodBeast() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
		GoodBeast goodBeast = new GoodBeast(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, goodBeast});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(masterSquirrel, new XY(1, 1));
		assertEquals(1200, masterSquirrel.getEnergy());
		assertEquals(new XY(6, 6), masterSquirrel.getXY());
		verify(board).remove(goodBeast);
	}

	@Test
	void testCollisionBadBeast() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
		BadBeast badBeast = new BadBeast(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, badBeast});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(masterSquirrel, new XY(1,1));
		assertEquals(850, masterSquirrel.getEnergy());
		assertEquals(new XY(5, 5), masterSquirrel.getXY());
		verify(board, never()).remove(badBeast);
	}

	@Test
	void testCollisionOwnMiniSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(300, new XY(6,6), masterSquirrel);
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, miniSquirrel});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(masterSquirrel, new XY(1,1));
		assertEquals(1300, masterSquirrel.getEnergy());
		assertEquals(new XY(6, 6), masterSquirrel.getXY());
		verify(board).remove(miniSquirrel);
	}

	@Test
	void testCollisionBadMiniSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
		HandOperatedMasterSquirrel badMasterSquirrel = new HandOperatedMasterSquirrel(new XY(9, 9));
		HandOperatedMiniSquirrel badMiniSquirrel = new HandOperatedMiniSquirrel(300, new XY(6,6), badMasterSquirrel);
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, badMiniSquirrel});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(masterSquirrel, new XY(1,1));
		assertEquals(1150, masterSquirrel.getEnergy());
		assertEquals(new XY(6, 6), masterSquirrel.getXY());
		verify(board).remove(badMiniSquirrel);
	}

	@Test
	void testCollisionBadMasterSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
		HandOperatedMasterSquirrel badMasterSquirrel = new HandOperatedMasterSquirrel(new XY(6, 6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, badMasterSquirrel});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(masterSquirrel, new XY(1,1));
		assertEquals(1000, masterSquirrel.getEnergy());
		assertEquals(1000, badMasterSquirrel.getEnergy());
		assertEquals(new XY(5, 5), masterSquirrel.getXY());
		assertEquals(new XY(6, 6), badMasterSquirrel.getXY());
		verify(board, never()).remove(any(MasterSquirrel.class));
		
	}

	@Test
	void testMasterSquirrelInvicible() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(5, 5));
		BadBeast badBeast = new BadBeast(new XY(6, 6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {masterSquirrel, badBeast});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		masterSquirrel.updateEnergy(-800);
		assertEquals(200, masterSquirrel.getEnergy());
		
		flatBoard.tryMove(masterSquirrel, new XY(1,1));
		assertEquals(new XY(5, 5), masterSquirrel.getXY());
		assertEquals(50, masterSquirrel.getEnergy());
	
		flatBoard.tryMove(masterSquirrel, new XY(1,1));
		assertEquals(new XY(5, 5), masterSquirrel.getXY());
		assertEquals(0, masterSquirrel.getEnergy());
		
		verify(board, never()).remove(masterSquirrel);
		verify(board, never()).remove(badBeast);
	}
	
}
