package de.hsa.games.fatsquirrel.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.util.XY;

class FlattenedBoardTest {

	private final Board board = new Board(new BoardConfig());
	
	@Test
	void testTryMoveMasterSquirrelXY() {
		for(Entity e : board.getEntitys())
			if(e instanceof MasterSquirrel) {
				XY MasterPos = e.getXY();
			}
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
