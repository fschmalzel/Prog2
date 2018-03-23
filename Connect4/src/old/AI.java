package old;
import java.util.Random;

public class AI {
	
	private static Random rand = new Random();
	
	public static int getInt(char[][] board, char player) {
		
		int num;
		
		do {
			num = rand.nextInt(board.length) + 1;
		} while (board[num - 1][0] != '.');
		
		return num;
		
	}
	
}
