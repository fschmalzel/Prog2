
public class Gameboard {
	
	private char[][] board;
	private final int length;
	private final int height;
	private static final int DEFAULT_LENGTH = 5;
	private static final int DEFAULT_HEIGHT = 5;
	
	public Gameboard(int length, int height)
	{
		if(length < 0 || height < 0) {
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
	
	public int insert(int n, char player) {
		
		if (n < 1 || n > board.length)
			return -2;
		
		n = n - 1;
		int runner = height-1;
		
		while (board[n][runner] != '.') {
			
			runner--;
			
			if (runner < 0) {
				return -1;
			}
			
		}
		
		board[n][runner] = player;
		
		if (hasWon(n, runner, player)) {
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
		
		
		return false;
		//TODO check
	}
	
	public char[][] getBoardArray() {
		char[][] copy = new char[length][height];
		for (int i = 0; i < length; i++)
			for (int z = 0; z < height; z++)
				copy[i][z] = board[i][z];
		return copy;
	}

}
