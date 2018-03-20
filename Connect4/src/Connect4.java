import java.io.IOException;

public class Connect4 {
	public static void main(String[] args) throws IOException {
		Gameboard board = new Gameboard(5,5);
		
		InOut.draw(board.getBoardArray());
		while (true) {
		board.insert(InOut.getInt(), 'x');
		InOut.draw(board.getBoardArray());
		}
		
		
		
		
	}

}
