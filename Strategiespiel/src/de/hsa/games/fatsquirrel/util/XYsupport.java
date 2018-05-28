package de.hsa.games.fatsquirrel.util;

import java.util.Random;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.Entity;

public class XYsupport {

	public static XY getRandomVector() {
		Random rand = new Random();
		int x = rand.nextInt(3) - 1;
		int y = rand.nextInt(3) - 1;
		
		return new XY(x, y);
	}
	
	public static XY getRandomCoordinates(Board board) {
		Random rnd = new Random();
		XY xy;
		XY size = board.getConfig().getSize();
		
		do {
			
			int x = rnd.nextInt(size.x - 2) + 1;
			int y = rnd.nextInt(size.y - 2) + 1;
			
			xy = new XY(x,y);
			
			for (Entity e : board.getEntitys())
				if (e.getXY().equals(xy)) {
					continue;
				}
			break;
			
		} while (true);
		
		return xy;
	}

}
