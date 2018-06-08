package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
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
		//TODO implement
	}
	
	@Test
	void testIsChild() {
		//TODO implement
	}
	
}
