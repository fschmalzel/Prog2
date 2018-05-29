package de.hsa.games.fatsquirrel.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.util.XY;

class CharacterTest {
	
	private class MasterSquirrelDummy extends MasterSquirrel{

		protected MasterSquirrelDummy(XY xy) {
			super(xy);
		}
		
	}
	
	final BadBeast bb = new BadBeast(new XY(1, 1));
	final GoodBeast gb = new GoodBeast(new XY(1, 1));
	final MasterSquirrel ms = new MasterSquirrelDummy(new XY(1, 1));
	final MiniSquirrel mis = new MiniSquirrel(100, new XY(1, 1), 2);
	
	@Test
	void testNextStepBadBeast() {
		fail("Not yet implemented");
	}

}
