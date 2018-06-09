package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.HandOperatedMiniSquirrel;
import de.hsa.games.fatsquirrel.util.XY;

class MiniSquirrelTest {

	@Test
	void testNextStepMiniSquirrel() {
		EntityContext con = mock(EntityContext.class);
		when(con.getSize()).thenReturn(new XY(10, 10));
		
		HandOperatedMiniSquirrel mini = new HandOperatedMiniSquirrel(100, new XY(5,6), new HandOperatedMasterSquirrel(new XY(2,3)));

		mini.setMoveCommand(new MoveCommand(new XY(1,1)));
		mini.nextStep(con);
		
		verify(con).tryMove(mini, new XY(1,1));
	}
	
	@Test
	void testStun() {		
		EntityContext con = mock(EntityContext.class);
		when(con.getSize()).thenReturn(new XY(10, 10));

		HandOperatedMiniSquirrel mini = new HandOperatedMiniSquirrel(100, new XY(3,3), new HandOperatedMasterSquirrel(new XY(4,3)));
		
		mini.stun();
		
		for(int i = 0; i < 3; i++) {
			assertTrue(mini.isStunned());
			mini.nextStep(con);
		}
		
		assertFalse(mini.isStunned());	}
	
	@Test
	void testGetMaster() {
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(5,6));
		HandOperatedMiniSquirrel mini = new HandOperatedMiniSquirrel(100, new XY(3,3), master);
		
		assertEquals(mini.getMaster(), master);
	}
	
	@Test
	void testHasSameMaster() {
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(5,6));
		HandOperatedMiniSquirrel mini = new HandOperatedMiniSquirrel(100, new XY(3,3), master);
		HandOperatedMiniSquirrel friendlyMini = new HandOperatedMiniSquirrel(100, new XY(7,3), master);
		HandOperatedMiniSquirrel evilMini = new HandOperatedMiniSquirrel(100, new XY(3,3), new HandOperatedMasterSquirrel(new XY(2,9)));

		assertTrue(mini.hasSameMaster(friendlyMini));
		assertFalse(mini.hasSameMaster(evilMini));
		
			}
	
	@Test
	void testDiesAfterTooManySteps() {
		EntityContext con = mock(EntityContext.class);
		when(con.getSize()).thenReturn(new XY(10, 10));
		
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(5,6));
		HandOperatedMiniSquirrel mini = new HandOperatedMiniSquirrel(1, new XY(3,3), master);
		
		mini.nextStep(con);
		
		verify(con).kill(mini);
	}
	
}
