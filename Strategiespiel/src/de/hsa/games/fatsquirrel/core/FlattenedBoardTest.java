package de.hsa.games.fatsquirrel.core;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.util.XY;

class FlattenedBoardTest {
	
	class Board() {
		
	}

	@Test
	void testTryMoveMasterSquirrelXY() {
		Board board = mock(Board.class);
		BoardConfig cfg = mock(BoardConfig.class);
		EntitySet set = new EntitySet();
		HandOperatedMasterSquirrel ms = new HandOperatedMasterSquirrel(new XY(5, 5));
		BadBeast badBeast = new BadBeast(new XY(7,7));
		GoodBeast goodBeast = new GoodBeast(new XY(6,7));
		
		set.insert(ms);
		set.insert(badBeast);
		set.insert(goodBeast);
		
		when(cfg.getSize()).thenReturn(new XY(20, 20));
		when(board.getConfig()).thenReturn(cfg);
		when(board.getEntitys()).thenReturn(set.toArray());
		when(board.remove(goodBeast)).then(set.remove(goodBeast))
		
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
		

	}

	@Test
	void testTryMoveMiniSquirrelXY() {
		fail("Not yet implemented");

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
		fail("Not yet implemented");
	}

	@Test
	void testTryInsert() {
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

	@Test
	void testGetMaster() {
		fail("Not yet implemented");
	}

}
