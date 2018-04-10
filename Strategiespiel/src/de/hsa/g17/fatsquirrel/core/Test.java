package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.BadBeast;
import de.hsa.g17.fatsquirrel.entities.BadPlant;
import de.hsa.g17.fatsquirrel.entities.GoodBeast;
import de.hsa.g17.fatsquirrel.entities.GoodPlant;
import de.hsa.g17.fatsquirrel.entities.HandOperatedMasterSquirrel;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("randomMovementTest " + (randomMovementTest() ? "successful!" : "failed!"));
		System.out.println("isSameTypeTest " + ((isSameTypeTest() == 0) ? "successful!" : "failed!"));
//		entitySetTest();
//		playerInputTest();

	}
	
	private static void entitySetTest() {
		EntitySet es = new EntitySet();
		
		Entity e = new BadPlant(3, 4);
		
		es.insert(new GoodBeast(3, 4));
		es.insert(new BadBeast(3, 4));
		es.insert(new GoodPlant(4, 4));
		es.insert(e);
		es.insert(new HandOperatedMasterSquirrel(3, 4));
		
		System.out.println(es);
		
		es.nextStep();

		System.out.println(es);
		System.out.println("Deleting: " + e);
		es.remove(e);

		System.out.println(es);
		
		es.nextStep();

		System.out.println(es);
	}
	
	private static void playerInputTest() {
		Entity e = new HandOperatedMasterSquirrel(5, 5);
		System.out.println(e);
		while (true) {
			e.nextStep();
			System.out.println(e);
		}
	}
	
	private static boolean randomMovementTest() {
		Entity e = new GoodBeast(3, 4);
		XY prev;
		for (int i = 0; i < 10; i++) {
			prev = e.getXY();
//			System.out.println(e);
			e.nextStep();
			if (e.getXY().equals(prev)) return false;
		}
//		System.out.println(e);
		return true;
	}
	
	private static int isSameTypeTest() {
		BadBeast b1 = new BadBeast(2, 3);
		BadBeast b2 = new BadBeast(6, 1);
		
		GoodBeast g1 = new GoodBeast(5, 4);
		
		Entity eb1 = (Entity) b1;
		Entity eb2 = (Entity) b2;
		
		Entity eg1 = (Entity) g1;
		
		if ( !b1	.isSameType(b2) ) return 1;
		if ( !eb1	.isSameType(b2) ) return 2;
		if ( !b1	.isSameType(eb2) ) return 3;
		if ( !eb1	.isSameType(eb2) ) return 4;
		
		if ( b1		.isSameType(g1) ) return 5;
		if ( eb1	.isSameType(g1) ) return 6;
		if ( b1		.isSameType(eg1) ) return 7;
		if ( eb1	.isSameType(eg1) ) return 8;
		
		return 0;
	}

}
