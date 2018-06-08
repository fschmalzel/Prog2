package de.hsa.games.fatsquirrel.test;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.util.XY;

class BadBeastTest {

	@Test
	void testSimpleMove() {
		EntityContext con = mock(EntityContext.class);
		when(con.getSize()).thenReturn(new XY(10, 10));
		
		BadBeast badBeast = new BadBeast(new XY(1, 2));

		badBeast.nextStep(con);

		verify(con).tryMove(eq(badBeast), any(XY.class));
	}
	
	@Test
	void testEvery4Steps() {
		EntityContext con = mock(EntityContext.class);
		when(con.getSize()).thenReturn(new XY(10, 10));
		
		BadBeast badBeast = new BadBeast(new XY(3, 3));
		
		badBeast.nextStep(con);
		
		verify(con).tryMove(eq(badBeast), any(XY.class));
		
		for (int i = 0; i < 3; i++)
			badBeast.nextStep(con);
		
		verify(con, times(1)).tryMove(eq(badBeast), any(XY.class));
		
		badBeast.nextStep(con);

		verify(con, times(2)).tryMove(eq(badBeast), any(XY.class));
	}
	
	@Test
	void testBite() {
		//TODO Implement
	}
	
	@Test
	void testFalseBooleanAfter7Bites() {
		//TODO Implement
		
		//for i = 1 .. 7 
		// assertFalse(b)
		// boolean b = bites()
		
		// assertTrue(b)
	}
	
	@Test
	void testChaseSquirrel() {
		EntityContext con = mock(EntityContext.class);
		
		when(con.getSize()).thenReturn(new XY(10, 10));
		when(con.nearestSquirrel(new XY(3,3))).thenReturn(new HandOperatedMasterSquirrel(new XY(5,5)));
		
		BadBeast badBeast = new BadBeast(new XY(3, 3));
		
		badBeast.nextStep(con);
		
		verify(con).tryMove(badBeast, new XY(1, 1));
	}
	
}


















