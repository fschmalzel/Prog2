package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.entities.HandOperatedMiniSquirrel;
import de.hsa.games.fatsquirrel.util.XY;

class MasterSquirrelTest {

	@Test
	void testNextStepMasterSquirrel() {
		EntityContext con = mock(EntityContext.class);
		when(con.getSize()).thenReturn(new XY(10, 10));
		
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(5,6));
		master.setCommand(new MoveCommand(new XY(1,1)));
		master.nextStep(con);
		
		verify(con).tryMove(master, new XY(1,1));
	}
	
	@Test
	void testStun() {
		EntityContext con = mock(EntityContext.class);
		when(con.getSize()).thenReturn(new XY(10, 10));
		
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(5,6));
		
		master.stun();
		
		for(int i = 0; i < 3; i++) {
			assertTrue(master.isStunned());
			master.nextStep(con);
		}
		
		assertFalse(master.isStunned());
	}
	
	@Test
	void testNoNegativeEnergy() {
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(5,6));
		master.updateEnergy(-1000);
		
		assertEquals(master.getEnergy(), 0);
		
		master.updateEnergy(-100);
		
		assertEquals(master.getEnergy(), 0);
	}
	
	@Test
	void testIsChild() {
		HandOperatedMasterSquirrel master = new HandOperatedMasterSquirrel(new XY(5,6));
		HandOperatedMiniSquirrel mini = new HandOperatedMiniSquirrel(100, new XY(3,5), master);
		HandOperatedMiniSquirrel evilMini = new HandOperatedMiniSquirrel(100, new XY(2,5), new HandOperatedMasterSquirrel(new XY(7,1)));

		assertTrue(master.isChild(mini));
		assertFalse(master.isChild(evilMini));
	}
	
}
