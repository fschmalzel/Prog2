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
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.HandOperatedMiniSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;

public class CollisionsMiniSquirrelTest {

	BoardConfig cfg;
	
	@BeforeEach
	void setUp() {
		cfg = mock(BoardConfig.class);
		when(cfg.getSize()).thenReturn(new XY(20, 20));
	}
	
	@Test
	void testTryMoveMiniSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
	
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel});
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1, 1));
		assertEquals(new XY(6, 6),  miniSquirrel.getXY());
		assertEquals(200, miniSquirrel.getEnergy());
	
	}

	@Test
	void testCollisionWall() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		Wall wall = new Wall(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, wall});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1,1));
		assertEquals(new XY(5,5), miniSquirrel.getXY());
		assertEquals(190, miniSquirrel.getEnergy());
		verify(board, never()).remove(wall);
		assertTrue(miniSquirrel.isStunned());
	}

	@Test
	void testCollisionGoodPlant() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		GoodPlant goodPlant = new GoodPlant(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, goodPlant});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1,1));
		assertEquals(new XY(6,6), miniSquirrel.getXY());
		assertEquals(300, miniSquirrel.getEnergy());
		verify(board).remove(goodPlant);
	}

	@Test
	void testCollisionBadPlant() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		BadPlant badPlant = new BadPlant(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, badPlant});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1,1));
		assertEquals(100, miniSquirrel.getEnergy());
		assertEquals(new XY(6,6), miniSquirrel.getXY());
		verify(board).remove(badPlant);
	}

	@Test
	void testCollisionGoodBeast() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		GoodBeast goodBeast = new GoodBeast(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, goodBeast});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1, 1));
		assertEquals(400, miniSquirrel.getEnergy());
		assertEquals(new XY(6, 6), miniSquirrel.getXY());
		verify(board).remove(goodBeast);
	}

	@Test
	void testCollisionBadBeast() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		BadBeast badBeast = new BadBeast(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, badBeast});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1,1));
		assertEquals(50, miniSquirrel.getEnergy());
		assertEquals(new XY(5, 5), miniSquirrel.getXY());
		verify(board, never()).remove(badBeast);
	}

	@Test
	void testCollisionOwnMiniSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		HandOperatedMiniSquirrel miniSquirrel2 = new HandOperatedMiniSquirrel(200, new XY(6, 6), masterSquirrel);
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, miniSquirrel2});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1,1));
		assertEquals(200, miniSquirrel.getEnergy());
		assertEquals(new XY(5, 5), miniSquirrel.getXY());
		assertEquals(200, miniSquirrel2.getEnergy());
		assertEquals(new XY(6, 6), miniSquirrel2.getXY());
		verify(board, never()).remove(any(MiniSquirrel.class));
	}

	@Test
	void testCollsionBadMiniSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		HandOperatedMasterSquirrel badMasterSquirrel = new HandOperatedMasterSquirrel(new XY(9, 9));
		HandOperatedMiniSquirrel badMiniSquirrel = new HandOperatedMiniSquirrel(200, new XY(6, 6), badMasterSquirrel);
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, badMiniSquirrel});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1,1));
		verify(board).remove(miniSquirrel);
		verify(board).remove(badMiniSquirrel);
	}

	@Test
	void testCollisionOwnMasterSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(6, 6));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, masterSquirrel});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1,1));
		verify(board).remove(miniSquirrel);
		verify(board, never()).remove(masterSquirrel);
		assertEquals(1200, masterSquirrel.getEnergy());
	}

	@Test
	void testCollsisionBadMasterSquirrel() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(200, new XY(5, 5), masterSquirrel);
		HandOperatedMasterSquirrel badMasterSquirrel = new HandOperatedMasterSquirrel(new XY(6, 6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, badMasterSquirrel});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(miniSquirrel, new XY(1,1));
		verify(board).remove(miniSquirrel);
		verify(board, never()).remove(badMasterSquirrel);
		assertEquals(1000, badMasterSquirrel.getEnergy());
	}

	@Test
	void testMiniSquirrelDeath() {
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8, 8));
		HandOperatedMiniSquirrel miniSquirrel = new HandOperatedMiniSquirrel(100, new XY(5, 5), masterSquirrel);
		Wall wall = new Wall(new XY(6,6));
		
		Board board = mock(Board.class);
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(new Entity[] {miniSquirrel, wall});
	
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		miniSquirrel.updateEnergy(-90);
		assertEquals(10, miniSquirrel.getEnergy());
		
		flatBoard.tryMove(miniSquirrel, new XY(1,1));
		verify(board, never()).remove(wall);
		verify(board).remove(miniSquirrel);
	}

}
