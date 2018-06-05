package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Squirrel;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;

class FlattenedBoardTest {

	@Test
	void testTryMoveMasterSquirrelXY() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		HandOperatedMasterSquirrel ms = new HandOperatedMasterSquirrel(new XY(5, 5));
		BadBeast badBeast = new BadBeast(new XY(7,7));
		GoodBeast goodBeast = new GoodBeast(new XY(6,7));
		BadPlant badPlant = new BadPlant(new XY(7,6));
		GoodPlant goodPlant = new GoodPlant(new XY(8,6));
		
		
		set.add(ms);
		set.add(badBeast);
		set.add(goodBeast);
		set.add(badPlant);
		set.add(goodPlant);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		//TODO
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		//No collision
		flatBoard.tryMove(ms, new XY(1, 1));
		
		assertEquals(new XY(6, 6),  ms.getXY());
		assertEquals(1000, ms.getEnergy());
		
		//Collision with BadBeast
		flatBoard.tryMove(ms, new XY(1,1));
		
		assertEquals(850, ms.getEnergy());
		assertEquals(new XY(6, 6), ms.getXY());
		
		//Collision with GoodBeast
		flatBoard.tryMove(ms, new XY(0, 1));
		assertEquals(1050, ms.getEnergy());
		assertEquals(new XY(6, 7), ms.getXY());
		verify(board).remove(goodBeast);
		
		set.remove(goodBeast);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		//Collision with BadPlant
		flatBoard.tryMove(ms, new XY(1,-1));
		assertEquals(950, ms.getEnergy());
		assertEquals(new XY(7,6), ms.getXY());
		verify(board).remove(badPlant);
		
		set.remove(badPlant);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		//Collision with GoodPlant
		flatBoard.tryMove(ms, new XY(1,0));
		assertEquals(new XY(8,6), ms.getXY());
		assertEquals(1050, ms.getEnergy());
		verify(board).remove(goodPlant);

		set.remove(goodPlant);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));

	}

	@Test
	void testTryMoveMiniSquirrelXY() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8,7));
		MiniSquirrel s = new MiniSquirrel(200, new XY(5, 5), masterSquirrel);
		BadBeast badBeast = new BadBeast(new XY(7,7));
		GoodBeast goodBeast = new GoodBeast(new XY(6,7));
		BadPlant badPlant = new BadPlant(new XY(7,6));
		BadPlant badPlant2 = new BadPlant(new XY (8, 5));
		GoodPlant goodPlant = new GoodPlant(new XY(8,6));
		HandOperatedMasterSquirrel evilMasterSquirrel = new HandOperatedMasterSquirrel(new XY(9,7));
		MiniSquirrel evilMini = new MiniSquirrel(100, new XY(9,6), evilMasterSquirrel);
		
		set.add(s);
		set.add(masterSquirrel);
		set.add(badBeast);
		set.add(goodBeast);
		set.add(badPlant);
		set.add(badPlant2);
		
		set.add(goodPlant);
		set.add(evilMini);
		set.add(evilMasterSquirrel);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));

		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		//Collision with nothing
		flatBoard.tryMove(s, new XY(1,1));
		assertEquals(new XY(6, 6),  s.getXY());
		assertEquals(200, s.getEnergy());
		
		//Collision with BadBeast
		flatBoard.tryMove(s, new XY(1,1));
		assertEquals(50, s.getEnergy());
		assertEquals(new XY(6, 6), s.getXY());
		
		//Collision with GoodBeast
		flatBoard.tryMove(s, new XY(0, 1));
		assertEquals(250, s.getEnergy());
		assertEquals(new XY(6, 7), s.getXY());
		verify(board).remove(goodBeast);
		
		set.remove(goodBeast);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		//Collision with BadPlant
		flatBoard.tryMove(s, new XY(1,-1));
		assertEquals(150, s.getEnergy());
		assertEquals(new XY(7,6), s.getXY());
		verify(board).remove(badPlant);
		
		set.remove(badPlant);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		//Collision with GoodPlant
		flatBoard.tryMove(s, new XY(1,0));
		assertEquals(new XY(8,6), s.getXY());
		assertEquals(250, s.getEnergy());
		verify(board).remove(goodPlant);
		
		set.remove(goodPlant);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		//Collision with own MasterSquirrel
		flatBoard.tryMove(s, new XY(0,1));
		assertEquals(new XY(8,6), s.getXY());
		assertEquals(250, s.getEnergy());
		assertEquals(1250, masterSquirrel.getEnergy());
		verify(board).remove(s);
		
		//Collision with other MasterSquirrel
		flatBoard.tryMove(s, new XY(1,1));
		verify(board, times(2)).remove(s);
		assertEquals(new XY(8,6), s.getXY());
		
		//Collision with other MiniSquirrel
		flatBoard.tryMove(s, new XY(1,0));
		verify(board, times(3)).remove(s);
		verify(board).remove(evilMini);
		assertEquals(new XY(8,6), s.getXY());
		
		//Death
		s.updateEnergy(10-s.getEnergy());
		flatBoard.tryMove(s, new XY(0, -1));
		verify(board, times(4)).remove(s);
		
	}

	@Test
	void testTryMoveGoodBeastXY() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		GoodBeast goodBeast = new GoodBeast(new XY(4,5));
		Wall wall = new Wall(new XY(4, 4));
		MasterSquirrel s = new HandOperatedMasterSquirrel(new XY(18,18));
		MiniSquirrel miniSquirrel = new MiniSquirrel(200, new XY(6,6), s);
		set.add(wall);
		set.add(goodBeast);
		set.add(miniSquirrel);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		//Collision with nothing
		flatBoard.tryMove(goodBeast, new XY(1,0));
		assertEquals(new XY(5,5), goodBeast.getXY());
		
		//Collision with Wall
		flatBoard.tryMove(goodBeast, new XY(-1, -1));
		assertEquals(new XY(5,5), goodBeast.getXY());
		
		//Collision with Squirrel
		flatBoard.tryMove(goodBeast, new XY(1, 1));
		verify(board).remove(goodBeast);
		assertEquals(400, miniSquirrel.getEnergy());
		
	}

	@Test
	void testTryMoveBadBeastXY() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		BadBeast badBeast = new BadBeast(new XY(4,5));
		Wall wall = new Wall(new XY(4, 4));
		MasterSquirrel s = new HandOperatedMasterSquirrel(new XY(18,18));
		MiniSquirrel miniSquirrel = new MiniSquirrel(10000, new XY(6,6), s);
		set.add(wall);
		set.add(badBeast);
		set.add(miniSquirrel);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		//Collision with nothing
		flatBoard.tryMove(badBeast, new XY(1,0));
		assertEquals(new XY(5,5), badBeast.getXY());
		
		//Collision with Wall
		flatBoard.tryMove(badBeast, new XY(-1, -1));
		assertEquals(new XY(5,5), badBeast.getXY());
		
		//Collision with Squirrel
		for (int i = 1; i <= 6; i++) {
			flatBoard.tryMove(badBeast, new XY(1, 1));
			verify(board, never()).remove(badBeast);
		}
		
		flatBoard.tryMove(badBeast, new XY(1, 1));
		verify(board).remove(badBeast);
		
		assertEquals(10000-7*150, miniSquirrel.getEnergy());
	}

	@Test
	void testNearestSquirrel() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		BadBeast badBeast = new BadBeast(new XY(3,3));
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(7,7));
		MiniSquirrel s = new MiniSquirrel(200, new XY(5, 5), masterSquirrel);
		
		set.add(badBeast);
		set.add(masterSquirrel);
		set.add(s);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);

		Squirrel nearestSquirrel = flatBoard.nearestSquirrel(badBeast.getXY());
		
		assertEquals(s, nearestSquirrel);
	
		
	}

	@Test
	void testTryInsert() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(7,7));
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		assertTrue(flatBoard.tryInsert(masterSquirrel));
		verify(board).insert(masterSquirrel);
		set.add(masterSquirrel);
		
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		assertFalse(flatBoard.tryInsert(goodBeast));
		verify(board, never()).insert(goodBeast);
	}

	@Test
	void testKill() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		set.add(goodBeast);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.kill(goodBeast);
		verify(board).remove(goodBeast);

	}

	@Test
	void testKillAndReplace() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		set.add(goodBeast);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.killAndReplace(goodBeast);
		verify(board).remove(goodBeast);
		verify(board).insert(Mockito.any(GoodBeast.class));
		
	}
		

	@Test
	void testGetEntityType() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		set.add(goodBeast);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(EntityType.GOOD_BEAST,flatBoard.getEntityType(goodBeast.getXY()));
	}

	@Test
	void testGetEntity() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		set.add(goodBeast);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(goodBeast, flatBoard.getEntity(goodBeast.getXY()));
	}

	@Test
	void testGetSize() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(new XY(20,20),flatBoard.getSize());
	}

	@Test
	void testGetMaster() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		Set<Entity> set = new LinkedHashSet<>();
		
		HandOperatedMasterSquirrel ms = new HandOperatedMasterSquirrel(new XY(7,7));
		MiniSquirrel miniSquirrel = new MiniSquirrel(100, new XY(5,5), ms);
		
		set.add(ms);
		set.add(miniSquirrel);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray(new Entity[set.size()]));
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(ms, flatBoard.getMaster(miniSquirrel));
	}

}
