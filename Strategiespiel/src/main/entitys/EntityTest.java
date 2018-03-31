package main.entitys;

import main.XY;

public class EntityTest {

	public static void main(String[] args) {
		EntitySet es = new EntitySet();

		

	}
	
	private static void equalTypeTest() {
		Entity e = new BadBeast(1, 2, 3);
		BadBeast b = (BadBeast) e;
		GoodBeast b2 = new GoodBeast(2, new XY(4, 5));
		Entity e2 = b2;
		
		System.out.println(b.equalType(e));
		System.out.println(e.equalType(b));
		
		System.out.println(b2.equalType(e));
		System.out.println(e.equalType(b2));
		
		System.out.println(b.equalType(b2));
		System.out.println(b2.equalType(b));
		
		System.out.println(e.equalType(e2));
		System.out.println(e2.equalType(e));
		
	}

}
