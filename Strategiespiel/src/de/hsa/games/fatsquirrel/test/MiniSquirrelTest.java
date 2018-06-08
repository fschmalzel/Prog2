package de.hsa.games.fatsquirrel.test;

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
		//TODO implement
	}
	
	@Test
	void testGetMaster() {
		//TODO implement
	}
	
	@Test
	void testHasSameMaster() {
		//TODO implement
	}
	
	@Test
	void testDiesAfterTooManySteps() {
		//TODO implemnt
		
		//new mini (energy = 1)
		//nextStep()
		//verify(con).kill(mini)
	}
	
}
