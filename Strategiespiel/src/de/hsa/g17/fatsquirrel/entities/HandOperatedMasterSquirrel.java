package de.hsa.g17.fatsquirrel.entities;

import java.io.IOException;

import de.hsa.g17.fatsquirrel.core.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

	public HandOperatedMasterSquirrel(XY xy) {
		super(xy);
	}
	public HandOperatedMasterSquirrel(int x, int y) {
		super(x, y);
	}

	@Override
	public void nextStep() {
		System.out.println("Where do you want to go?\n"
				+ "123\n"
				+ "4S5\n"
				+ "678\n"
				+ "> ");
		
		int i;
		
		try {
			do {
				i = System.in.read();
			} while (i < '1' || i > '8');
			
			i -= '0';
			
		} catch (IOException e) {
			i = 2;
			e.printStackTrace();
		}
		
		// Richtungsvektor in X-Richtung
		// i Modulo 3 gibt Zahlen zwischen 0 und 2
		// i muss ab der 4 nur um eines verschoben werden, da
		// 5 nicht die Mitte ist sondern um eines verschoben ist
		int x = (i>4?i:i-1) % 3 - 1;
		
		// Richtungsvektor in Y-Richtung
//		int y = i>3?(i>5?1:0):-1;
		int y;
		
		if (i > 5)
			y = 1;
		else if (i > 3)
			y = 0;
		else
			y = -1;
		
		
		this.move(new XY(x, y));
		
	}

	public String toString() {
		return "HandOperatedMasterSquirrel" + super.toString();
	}
	
}
