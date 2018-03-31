package main;

import java.util.Random;

public class XY {
	private final int x;
	private final int y;
	
	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public XY move(XY vector) {
		return new XY(x + vector.x, y + vector.y);
	}
	
	public static XY getRandomVector() {
		Random rand = new Random();
		int x, y;
		
		do {
			x = rand.nextInt(2) - 1;
			y = rand.nextInt(2) - 1;
		} while(x == 0 && y == 0);
		
		return new XY(x, y);
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public String toString() {
		return "(" + x + "|" + y + ")";
	}
	
}
