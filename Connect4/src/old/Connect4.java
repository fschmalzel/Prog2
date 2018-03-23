package old;
import java.io.IOException;

public class Connect4 {
	
	public static void main(String[] args) throws IOException {
		
		Gameboard board = new Gameboard(6,5);
		
		boolean ai = false;
		
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("-ai")) {
				ai = true;
			}
		}
		
		char currPlayer = 'x';
		
		while (true) {
			int code;
			if (currPlayer == 'o' && ai) {
				code = board.insert(AI.getInt(board.getBoardArray(), currPlayer), currPlayer);
			} else {
				InOut.draw(board.getBoardArray());
				code = board.insert(InOut.getInt(), currPlayer);
			}
			
			
			if (code == -2) {
				InOut.errorTooBig();
				continue;
			} else if (code == -1) {
				InOut.errorFull();
				continue;
			} else if ( code == 1) {
				InOut.draw(board.getBoardArray());
				InOut.winMsg(currPlayer);
				break;
			} else if (code == -3) {
				InOut.draw(board.getBoardArray());
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
