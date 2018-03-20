
public class Connect4GameBoard {
	
	private char[][] board;
	private final int length;
	private final int height;
	public static final int DEFAULT_LENGTH = 5;
	public static final int DEFAULT_HEIGHT = 5;
	
	public Connect4GameBoard(int lenght, int height)
	{
		if(lenght < 0 || height < 0) {
			this.length = DEFAULT_LENGTH;
			this.height = DEFAULT_HEIGHT;
		}
		else {
			this.length = lenght;
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
	
	public void draw(){
		for(int i = 0; i < length; i++) {
			System.out.print(i+1);
		}
		System.out.println();
	
		for(int i = 0; i < length; i++) {
			for(int z = 0; z < height; z++){
				System.out.print(board[i][z]);
			}
			System.out.println();
			
		}
	}

}
