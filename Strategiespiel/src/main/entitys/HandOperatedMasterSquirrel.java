package main.entitys;

import java.io.IOException;

import main.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

	public HandOperatedMasterSquirrel(XY xy) {
		super(xy);
	}
	public HandOperatedMasterSquirrel(int x, int y) {
		super(x, y);
	}

	@Override
	void nextStep() {
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
		
		int x = (i>4?i:i-1) % 3 - 1;
		int y = i>3?(i>5?1:0):-1;
		
		this.move(new XY(x, y));
		
	}

	public String toString() {
		return "HandOperatedMasterSquirrel" + super.toString();
	}
	
}
