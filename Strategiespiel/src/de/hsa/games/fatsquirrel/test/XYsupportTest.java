package de.hsa.games.fatsquirrel.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

class XYsupportTest {
	
	@Test
	void testGetRandomVector() {
		boolean[][] testfield = new boolean[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				testfield[x][y] = false;
			}
		}
		
		boolean notAll;
		int counter = 0;
		
		do {
			notAll = false;
			XY xy = XYsupport.getRandomVector();
			testfield[xy.x+1][xy.y+1] = true;
			for (int x = 0; x < 3 && !notAll; x++) {
				for (int y = 0; y < 3; y++) {
					if (!testfield[x][y]) {
						notAll = true;
						break;
					}
				}
			}
			
			counter++;
			if (counter >= 100)
				fail("Bad luck or bad algorithm!");
			
		} while (notAll);
	}

	@Test
	void testGetRandomCoordinates() {
		
		boolean[][] testfield = new boolean[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				testfield[x][y] = false;
			}
		}
		
		boolean notAll;
		int counter = 0;
		
		do {
			notAll = false;
			XY xy = XYsupport.getRandomCoordinates(new XY(0, 0), new XY(2, 2));
			testfield[xy.x][xy.y] = true;
			for (int x = 0; x < 3 && !notAll; x++) {
				for (int y = 0; y < 3; y++) {
					if (!testfield[x][y]) {
						notAll = true;
						break;
					}
				}
			}
			
			counter++;
			if (counter >= 100)
				fail("Bad luck or bad algorithm!");
			
		} while (notAll);
		
	}

}
