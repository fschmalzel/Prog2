
public class Gameboard {
	
	private char[][] board;
	private final int length;
	private final int height;
	public static final int DEFAULT_LENGTH = 5;
	public static final int DEFAULT_HEIGHT = 5;
	
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
			return -1;
		
		n = n - 1;
		int runner = height-1;
		
		while (board[n][runner] != '.') {
			
			runner--;
			
			if (runner < 0) {
				return -1;
			}
			
		}
		
		board[n][runner] = player;
		
		if (hasWon(n, runner)) {
			return 1;
		}
		
		return 0;
	}
	
	private boolean hasWon(int column, int row) {
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
