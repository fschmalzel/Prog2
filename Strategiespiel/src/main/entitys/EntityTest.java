package main.entitys;

import main.XY;

public class EntityTest {

	public static void main(String[] args) {
		
		entitySetTest();
//		playerInputTest();
//		randomMovementTest();
//		equalTypeTest();

	}
	
	private static void entitySetTest() {
		EntitySet es = new EntitySet();
		
		Entity e = new BadPlant(4, 3, 4);
		
		es.insert(new GoodBeast(1, 3, 4));
		es.insert(new BadBeast(2, 3, 4));
		es.insert(new GoodPlant(3, 4, 4));
		es.insert(e);
		es.insert(new HandOperatedMasterSquirrel(5, 3, 4));
		
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
		Entity e = new HandOperatedMasterSquirrel(1, 5, 5);
		System.out.println(e);
		while (true) {
			e.nextStep();
			System.out.println(e);
		}
	}
	
	private static void randomMovementTest() {
		Entity e = new GoodBeast(1, 3, 4);
		for (int i = 0; i < 10; i++) {
			System.out.println(e);
			e.nextStep();
		}
		System.out.println(e);
	}
	
	private static void equalTypeTest() {
		Entity e = new BadBeast(1, 2, 3);
		BadBeast b = (BadBeast) e;
		GoodBeast b2 = new GoodBeast(2, new XY(4, 5));
		Entity e2 = b2;
		
		System.out.println(b.isSameType(e));
		System.out.println(e.isSameType(b));
		
		System.out.println(b2.isSameType(e));
		System.out.println(e.isSameType(b2));
		
		System.out.println(b.isSameType(b2));
		System.out.println(b2.isSameType(b));
		
		System.out.println(e.isSameType(e2));
		System.out.println(e2.isSameType(e));
		
	}

}
