package v2;

import java.util.Random;

public class RandomAI implements AI {
	
	private static Random rand = new Random();
	
	@Override
	public int getInt(char[][] board, char player) {
		
		int num;
		
		do {
			num = rand.nextInt(board.length) + 1;
		} while (board[num - 1][0] != '.');
		
		return num;
	}

}
