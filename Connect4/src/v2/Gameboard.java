package v2;

public class Gameboard {
	
	private char[][] board;
	private final int length;
	private final int height;
	private static final int DEFAULT_LENGTH = 5;
	private static final int DEFAULT_HEIGHT = 5;
	
	public Gameboard(int length, int height)
	{
		if(length < 4 || height < 4) {
			this.length = DEFAULT_LENGTH;
			this.height = DEFAULT_HEIGHT;
		}
		else {
			this.length = length;
			this.height = height;
		}
		createBoard();
	}
	
	private void createBoard() {
		board = new char[length][height];
		
		for(int i = 0; i < length; i++) {
			for(int z = 0; z < height; z++){
				board[i][z] = '.';
			}
		}
	}
	
	public int insert(int column, char player) {
		
		if (column < 1 || column > board.length)
			return -2;
		
		column = column - 1;
		int runner = height-1;
		
		while (board[column][runner] != '.') {
			
			runner--;
			
			if (runner < 0) {
				return -1;
			}
			
		}
		
		board[column][runner] = player;
		
		if (hasWon(column, runner, player)) {
			return 1;
		}
		
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == '.')
				return 0;
		}
		
		return -3;
	}
	
	private boolean hasWon(int column, int row, char player) {
		
		int count = 1;
		int runner = row + 1;
		while ( runner < board[0].length && board[column][runner] == player) {	//Player won 4 connect below
			count++;
			runner++;
			if (count == 4)
				return true;
		}
		
		count = 1;
		runner = column + 1;
		while ( runner < board.length && board[runner][row] == player) {	//Player won 4 connect right
			count++;
			runner++;
			if (count == 4)
				return true;
		}
		
		runner = column - 1;
		while ( runner >= 0 && board[runner][row] == player) {	//Player won 4 connect left
			count++;
			runner--;
			if (count == 4)
				return true;
		}
		
		count = 1;
		runner = -1;
		// links oben
		while (runner+row >= 0 && runner+column >= 0 && board[runner+column][runner+row] == player) {
			count++;
			runner--;
			if (count == 4)
				return true;
		}
		
		//rechts unten
		runner = 1;
		while (runner+column < board.length && runner+row < board[0].length && board[runner+column][runner+row] == player) {
			count++;
			runner++;
			if (count == 4)
				return true;
		}
		
		count = 1;
		runner = 1;
		//links unten
		// column -> 0
		// row -> board[0].length
		while(row+runner < board[0].length && column-runner >= 0 && board[column-runner][row+runner] == player) {
			count++;
			runner++;
			if (count == 4)
				return true;
		}
		
		//rechts oben
		// column -> length
		// row -> 0
		runner = 1;
		while (row-runner >= 0 && column+runner < board.length && board[column+runner][row-runner] == player) {
			count++;
			runner++;
			if (count == 4)
				return true;
		}
		
		
		return false;
	}
	
	public char[][] getBoardArray() {
		char[][] copy = new char[length][height];
		for (int i = 0; i < length; i++)
			for (int z = 0; z < height; z++)
				copy[i][z] = board[i][z];
		return copy;
	}

}
