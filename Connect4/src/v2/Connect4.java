package v2;
import java.io.IOException;

public class Connect4 {
	
	public static void main(String[] args) throws IOException {
		
		AI ai = null;
		
		if (args.length > 0) {
			if (args[0].startsWith("-ai=")) {
				String aiString = args[0].substring(4).toLowerCase();
				switch(aiString) {
				case "artur":
					ai = new ArturAI();
					break;
				case "felix":
					ai = new FelixAI();
					break;
				default:
				case "random":
					ai = new RandomAI();
					break;
				}
					
			}
		}
		
		play(ai, 5, 5);
		
	}
	
	
	private static void play(AI ai, int width, int height) throws IOException {
		
		char currPlayer = 'x';
		boolean finished = false;
		Gameboard board = new Gameboard(width,height);
		
		int column = 0;
		do {
			
			int code;
			// Get input
			if (currPlayer == 'o' && ai != null) {
				ai.playerMove(column);
				code = board.insert(ai.getInt(board.getBoardArray(), currPlayer), currPlayer);
			} else {
				InOut.draw(board.getBoardArray());
				column = InOut.getInt();
				code = board.insert(column, currPlayer);
			}
			
			// Check for special events
			switch(code) {
			case 1:
				// End of game, so we draw the board and finish the loop
				InOut.draw(board.getBoardArray());
				InOut.winMsg(currPlayer);
				finished = true;
				break;
			case -1:
				// Error, players will not be switched
				InOut.errorFull();
				continue;
			case -2:
				// Error, players will not be switched
				InOut.errorTooBig();
				continue;
			case -3:
				// End of game, so we draw the board and finish the loop
				InOut.draw(board.getBoardArray());
				InOut.drawMsg();
				finished = true;
				break;
			}

			
			// Switch players
			if (currPlayer == 'x') 
				currPlayer = 'o';
			else
				currPlayer = 'x';
			
		} while (!finished);
		
	}

}
