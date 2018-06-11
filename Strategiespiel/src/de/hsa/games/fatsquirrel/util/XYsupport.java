package de.hsa.games.fatsquirrel.util;

import java.util.Random;

public class XYsupport {
	
	private static Random rnd = new Random();
	
	/**
	 * Returns a randomVector ranging from (-1 | -1) to (1 | 1)
	 * 
	 * @return {@link XY} randomVector
	 */
	public static XY getRandomVector() {
		int x = rnd.nextInt(3) - 1;
		int y = rnd.nextInt(3) - 1;
		
		return new XY(x, y);
	}
	
	/**
	 * @param xy1 
	 * @param xy2
	 * @return A coordinate that is inside the coordinates from xy1 to xy2.
	 */
	public static XY getRandomCoordinates(XY xy1, XY xy2) {
		if (xy1.x > xy2.x || xy1.y > xy2.y)
			return null;
		
		return new XY(
				rnd.nextInt(xy2.x - xy1.x + 1) + xy1.x, 
				rnd.nextInt(xy2.y - xy1.y + 1) + xy1.y);
	}
	
	/**
	 * @param xy1
	 * @param xy2
	 * @param visibility The range.
	 * @return If xy2 is in range of xy1. 
	 */
	public static boolean isInView(XY xy1, XY xy2, int visibility) {

		if (Math.abs(xy1.x - xy2.x) > (visibility - 1) / 2)
			return false;
		else if (Math.abs(xy1.y - xy2.y) > (visibility - 1) / 2)
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
