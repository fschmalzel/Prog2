package v2;
import java.io.IOException;

public class Connect4 {
	
	public static void main(String[] args) throws IOException {
		
		AI ai = null;
		
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("-ai")) {
				ai = new randomAi();
			}
		}
		
		play(ai, 3, 1);
		
	}
	
	
	public static void play(AI ai, int width, int height) throws IOException {
		Gameboard board = new Gameboard(width,height);
		
		char currPlayer = 'x';
		
		while (true) {
			int code;
			if (currPlayer == 'o' && ai != null) {
				code = board.insert(ai.getInt(board.getBoardArray(), currPlayer), currPlayer);
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
