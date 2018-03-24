package v2;
import java.io.IOException;

public class Connect4 {
	
	public static void main(String[] args) throws IOException {
		
		AI ai = null;
		
		if (args.length > 0) {
			if (args[0].startsWith("-ai=")) {
				String aiString = args[0].substring(4).toLowerCase();
				switch(aiString) {
				
				default:
				case "random":
					ai = new RandomAI();
					break;
				}
					
			}
		}
		
		play(ai, 3, 1);
		
	}
	
	
	private static void play(AI ai, int width, int height) throws IOException {
		
		char currPlayer = 'x';
		boolean finished = false;
		Gameboard board = new Gameboard(width,height);
		
		do {
			
			int code;
			// Get input
			if (currPlayer == 'o' && ai != null) {
				code = board.insert(ai.getInt(board.getBoardArray(), currPlayer), currPlayer);
			} else {
				InOut.draw(board.getBoardArray());
				code = board.insert(InOut.getInt(), currPlayer);
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
		
		
		
//		while (true) {
//			int code;
//			if (currPlayer == 'o' && ai != null) {
//				code = board.insert(ai.getInt(board.getBoardArray(), currPlayer), currPlayer);
//			} else {
//				InOut.draw(board.getBoardArray());
//				code = board.insert(InOut.getInt(), currPlayer);
//			}
//			
//			
//			if (code == -2) {
//				InOut.errorTooBig();
//				continue;
//			} else if (code == -1) {
//				InOut.errorFull();
//				continue;
//			} else if ( code == 1) {
//				InOut.draw(board.getBoardArray());
//				InOut.winMsg(currPlayer);
//				break;
//			} else if (code == -3) {
//				InOut.draw(board.getBoardArray());
//				InOut.drawMsg();
//				break;
//			}
//			
//			if (currPlayer == 'x') 
//				currPlayer = 'o';
//			else
//				currPlayer = 'x';
//			
//		}
		
	}

}
