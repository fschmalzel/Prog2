package v2;

public class Gameboard {
	
	private char[][] board;
	private final int length;
	private final int height;
	private static final int DEFAULT_LENGTH = 5;
	private static final int DEFAULT_HEIGHT = 5;
	
	public Gameboard(int length, int height) {
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
		
		// Check if it is inside the board
		if (column < 1 || column > board.length)
			return -2;
		
		// The Array is 0-indexed, the player input isn't
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
		
		// Look if there are 3 of the same underneath the placed stone
		int count = 1;
		int runner = row + 1;
		while ( runner < board[0].length && board[column][runner] == player) {	//Player won 4 connect below
			count++;
			runner++;
			if (count == 4)
				return true;
		}
		
		// Look right and left for 4 in a row
		// Need to reset the counter
		count = 1;
		runner = column + 1;
		
		// Starting by going to the right
		while ( runner < board.length && board[runner][row] == player) {
			count++;
			runner++;
			if (count == 4)
				return true;
		}
		
		// Then left
		runner = column - 1;
		while ( runner >= 0 && board[runner][row] == player) {
			count++;
			runner--;
			if (count == 4)
				return true;
		}
		
		// Looking diagonally from top left to bottom right
		// Reset counter
		count = 1;
		runner = -1;
		// Starting from the placed stone going to the top left
		while (runner+row >= 0 && runner+column >= 0 && board[runner+column][runner+row] == player) {
			count++;
			runner--;
			if (count == 4)
				return true;
		}

		// Then starting from the placed stone going to the bottom right
		runner = 1;
		while (runner+column < board.length && runner+row < board[0].length && board[runner+column][runner+row] == player) {
			count++;
			runner++;
			if (count == 4)
				return true;
		}

		// Looking diagonally from top right to bottom left
		// Reset counter
		count = 1;
		runner = 1;
		
		// Starting from the placed stone going to the top right
		
		while(row+runner < board[0].length && column-runner >= 0 && board[column-runner][row+runner] == player) {
			count++;
			runner++;
			if (count == 4)
				return true;
		}
		
		// Then starting from the placed stone going to the bottom left
		runner = 1;
		while (row-runner >= 0 && column+runner < board.length && board[column+runner][row-runner] == player) {
			count++;
			runner++;
			if (count == 4)
				return true;
		}
		
		// If there were no 4 stones in a row, then return false
		return false;
	}
	
	public char[][] getBoardArray() {
		
		// Copy the array, so that an AI can't do anything to it.
		char[][] copy = new char[length][height];
		for (int i = 0; i < length; i++)
			for (int z = 0; z < height; z++)
				copy[i][z] = board[i][z];
		return copy;
		
	}

}
