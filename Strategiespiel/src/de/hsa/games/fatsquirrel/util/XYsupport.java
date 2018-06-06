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
		
		boolean badPos;
		
		do {
			badPos = false;
			int x = rnd.nextInt(size.x - 2) + 1;
			int y = rnd.nextInt(size.y - 2) + 1;
			
			xy = new XY(x,y);
			
			for (Entity e : board.getEntitys())
				if (e.getXY().equals(xy)) {
					badPos = true;
				}
			
		} while (badPos);
		
		return xy;
	}
	
	public static boolean isInView(XY xy1, XY xy2, int visibilty) {

		if (Math.abs(xy1.x - xy2.x) > (visibilty - 1) / 2)
			return false;
		else if (Math.abs(xy1.y - xy2.y) > (visibilty - 1) / 2)
			return false;

		return true;

	}
	
	public static XY getDirection(XY from, XY to) {
		int x = 0;
		int y = 0;
		
		if (from.x < to.x)
			x = 1;
		else if (from.x > to.x)
			x = -1;
		
		if (from.y < to.y)
			y = 1;
		else if (from.y > to.y)
			y = -1;
		
		return new XY(x,y);
		
	}

}
