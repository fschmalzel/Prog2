package v2;

public class ArturAI implements AI {
	
	private int lastColumn;
	
	@Override
	public int getInt(char[][] board, char player) {
		
		if (board[lastColumn-1][0] == '.')
			return lastColumn;
		
		int runner = 1;
		
		do {
			
			if (board.length >= lastColumn+runner && board[lastColumn+runner-1][0] == '.')
				return lastColumn+runner;
			
			if (0 < lastColumn-runner && board[lastColumn-runner-1][0] == '.')
				return lastColumn-runner;
			
			runner++;
			
		} while(runner+lastColumn > board.length && lastColumn-runner <= 0);
		
		return 1;
		
	}
	
	@Override
	public void playerMove(int column) {
		lastColumn = column;
	}

}
