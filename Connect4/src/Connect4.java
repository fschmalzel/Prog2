import java.io.IOException;

public class Connect4 {
	public static void main(String[] args) throws IOException {
		Gameboard board = new Gameboard(6,5);
		
		InOut.draw(board.getBoardArray());
		char currPlayer = 'x';
		
		while (true) {
			int code = board.insert(InOut.getInt(), currPlayer);
			InOut.draw(board.getBoardArray());
			
			if (code == -2) {
				InOut.errorTooBig();
				continue;
			} else if (code == -1) {
				InOut.errorFull();
				continue;
			} else if ( code == 1) {
				InOut.winMsg(currPlayer);
				break;
			} else if (code == -3) {
				InOut.drawMsg();
				break;
			}
			
			if (currPlayer == 'x') 
				currPlayer = 'o';
			else
				currPlayer = 'x';
			
		}
		
		
		
		
	}

}
