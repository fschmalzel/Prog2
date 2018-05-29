package de.hsa.games.fatsquirrel.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.*;
import org.easymock.*;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

class CharacterTest {
	
	private EntityContext entityContext;
	private BadBeast badBeast;
	
	@Before
	public void setUp() {
		entityContext = mock(EntityContext.class);
	}
	
	@Test
	void testNextStepBadBeast() {
		entityContext.tryMove(badBeast, new XY(1,1));
		
		
	}

}
