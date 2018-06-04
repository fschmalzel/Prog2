package de.hsa.games.fatsquirrel.core;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Squirrel;
import de.hsa.games.fatsquirrel.util.XY;

class FlattenedBoardTest {

	@Test
	void testTryMoveMasterSquirrelXY() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		HandOperatedMasterSquirrel ms = new HandOperatedMasterSquirrel(new XY(5, 5));
		BadBeast badBeast = new BadBeast(new XY(7,7));
		GoodBeast goodBeast = new GoodBeast(new XY(6,7));
		BadPlant badPlant = new BadPlant(new XY(7,6));
		GoodPlant goodPlant = new GoodPlant(new XY(8,6));
		
		
		set.insert(ms);
		set.insert(badBeast);
		set.insert(goodBeast);
		set.insert(badPlant);
		set.insert(goodPlant);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());

		//TODO
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(ms, new XY(1, 1));
		
		assertEquals(new XY(6, 6),  ms.getXY());

		assertEquals(1000, ms.getEnergy());
		flatBoard.tryMove(ms, new XY(1,1));
		
		assertEquals(850, ms.getEnergy());
		assertEquals(new XY(6, 6), ms.getXY());
		
		flatBoard.tryMove(ms, new XY(0, 1));
		assertEquals(1050, ms.getEnergy());
		assertEquals(new XY(6, 7), ms.getXY());
		
		flatBoard.tryMove(ms, new XY(1,-1));
		set.remove(badPlant);
		assertEquals(950, ms.getEnergy());
		assertEquals(new XY(7,6), ms.getXY());
		
		
		flatBoard.tryMove(ms, new XY(1,0));
		set.remove(goodPlant);
		assertEquals(new XY(8,6), ms.getXY());
		assertEquals(1050, ms.getEnergy());

	}

	@Test
	void testTryMoveMiniSquirrelXY() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(8,7));
		MiniSquirrel s = new MiniSquirrel(200, new XY(5, 5), masterSquirrel);
		BadBeast badBeast = new BadBeast(new XY(7,7));
		GoodBeast goodBeast = new GoodBeast(new XY(6,7));
		BadPlant badPlant = new BadPlant(new XY(7,6));
		GoodPlant goodPlant = new GoodPlant(new XY(8,6));
		HandOperatedMasterSquirrel evilMasterSquirrel = new HandOperatedMasterSquirrel(new XY(8,8));
		MiniSquirrel evilMini = new MiniSquirrel(100, new XY(8,9), evilMasterSquirrel);
		
		
		set.insert(s);
		set.insert(masterSquirrel);
		set.insert(badBeast);
		set.insert(goodBeast);
		set.insert(badPlant);
		set.insert(goodPlant);
		set.insert(evilMini);
		set.insert(evilMasterSquirrel);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());

		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.tryMove(s, new XY(1,1));
		
		assertEquals(new XY(6, 6),  s.getXY());

		assertEquals(200, s.getEnergy());
		flatBoard.tryMove(s, new XY(1,1));
		
		assertEquals(50, s.getEnergy());
		assertEquals(new XY(6, 6), s.getXY());
		
		flatBoard.tryMove(s, new XY(0, 1));
		assertEquals(250, s.getEnergy());
		assertEquals(new XY(6, 7), s.getXY());
		
		flatBoard.tryMove(s, new XY(1,-1));
		set.remove(badPlant);
		assertEquals(150, s.getEnergy());
		assertEquals(new XY(7,6), s.getXY());
		
		
		flatBoard.tryMove(s, new XY(1,0));
		set.remove(goodPlant);
		assertEquals(new XY(8,6), s.getXY());
		assertEquals(250, s.getEnergy());
		
		flatBoard.tryMove(s, new XY(0,1));
		
		for(Entity e : set.toArray())
			if(e.equals(s))
				fail("Squirrel isn't dead");
		
		assertEquals(new XY(8,6), s.getXY());
		assertEquals(250, s.getEnergy());
		assertEquals(1250, masterSquirrel.getEnergy());
		
		set.insert(s);
		
		flatBoard.tryMove(s, new XY(0,2));
		
		for(Entity e : set.toArray())
			if(e.equals(s))
				fail("Squirrel isn't dead");
		
		assertEquals(new XY(8,6), s.getXY());
		
		set.insert(s);
		
		flatBoard.tryMove(s, new XY(0,3));
		
		for(Entity e : set.toArray())
			if(e.equals(s))
				fail("Squirrel isn't dead");
		
		assertEquals(new XY(8,6), s.getXY());
		//TODO muss erst remove funktionieren
	}

	@Test
	void testTryMoveGoodBeastXY() {
		fail("Not yet implemented");
	}

	@Test
	void testTryMoveBadBeastXY() {
		fail("Not yet implemented");
	}

	@Test
	void testNearestSquirrel() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		BadBeast badBeast = new BadBeast(new XY(3,3));
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(7,7));
		MiniSquirrel s = new MiniSquirrel(200, new XY(5, 5), masterSquirrel);
		
		set.insert(badBeast);
		set.insert(masterSquirrel);
		set.insert(s);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);

		Squirrel nearestSquirrel = flatBoard.nearestSquirrel(badBeast.getXY());
		
		assertEquals(s, nearestSquirrel);
	
		
	}

	@Test
	void testTryInsert() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		HandOperatedMasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(new XY(7,7));

		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertTrue(flatBoard.tryInsert(masterSquirrel));
	}

	@Test
	void testKill() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		set.insert(goodBeast);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.kill(goodBeast);
		
		for(Entity e : set.toArray())
			if(e.equals(goodBeast))
				fail("GoodBeast isn't dead");

	}

	@Test
	void testKillAndReplace() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		set.insert(goodBeast);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		flatBoard.kill(goodBeast);
		
		for(Entity e : set.toArray())
			if(e.equals(goodBeast))
				break;
		assertEquals(new XY(7,7), goodBeast.getXY());
		
	}
		

	@Test
	void testGetEntityType() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		set.insert(goodBeast);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(EntityType.GOOD_BEAST,flatBoard.getEntityType(goodBeast.getXY()));
	}

	@Test
	void testGetEntity() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		GoodBeast goodBeast = new GoodBeast(new XY(7,7));

		set.insert(goodBeast);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(goodBeast, flatBoard.getEntity(goodBeast.getXY()));
	}

	@Test
	void testGetSize() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());	
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(new XY(20,20),flatBoard.getSize());
	}

	@Test
	void testGetMaster() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		
		HandOperatedMasterSquirrel ms = new HandOperatedMasterSquirrel(new XY(7,7));
		MiniSquirrel miniSquirrel = new MiniSquirrel(100, new XY(5,5), ms);
		
		set.insert(ms);
		set.insert(miniSquirrel);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());
		
		FlattenedBoard flatBoard = new FlattenedBoard(board);
		
		assertEquals(ms, flatBoard.getMaster(miniSquirrel));
	}

}
