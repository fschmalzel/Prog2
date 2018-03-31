package main.entitys;

import java.io.IOException;

import main.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

	public HandOperatedMasterSquirrel(int id, XY xy) {
		super(id, xy);
	}
	public HandOperatedMasterSquirrel(int id, int x, int y) {
		super(id, x, y);
	}

	@Override
	void nextStep() {
		/*
		 * 123
		 * 4O5
		 * 678
		 */
		
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
		
		int x = (i>4?i:i-1) % 3 - 1;
		int y = i>3?(i>5?1:0):-1;
		
		this.move(new XY(x, y));
		
	}

	public String toString() {
		return "HandOperatedMasterSquirrel" + super.toString();
	}
	
}
