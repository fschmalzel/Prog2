import java.io.IOException;

public class Connect4 {
	public static void main(String[] args) throws IOException {
		Connect4GameBoard board = new Connect4GameBoard(5,5);
		board.draw();
	}

}
