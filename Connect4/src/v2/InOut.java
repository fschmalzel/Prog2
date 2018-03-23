package v2;
import java.io.IOException;

public class InOut {
	
	public static int getInt() throws IOException{
		int i;
		do {
			System.out.println();
			System.out.print(">");
			do {
				i = System.in.read();
			} while (i == '\n' || i == '\r');
			
			if (i <= '0' || i > '9')
				errorNaN();
				
		} while (i <= '0' || i > '9');
		
		i -= '0';
		System.out.println();
		return i;
		
	}
	
	public static void errorNaN() {
		System.out.println("Not a number, try again!");
	}
	
	public static void errorTooBig() {
		System.out.println("Number too big, try again!");
	}
	
	public static void errorFull() {
		System.out.println("Column is full, try again!");
	}
	
	public static void draw(char[][] boardArray) {
		
		for(int i = 0; i < boardArray.length; i++)
			System.out.print(i+1);
		System.out.println();
		
		for(int i = 0; i < boardArray[0].length; i++) {
			
			for(int z = 0; z < boardArray.length; z++)
				System.out.print(boardArray[z][i]);
			
			System.out.println();
		}
		
		for(int i = 0; i < boardArray.length; i++)
			System.out.print(i+1);
		System.out.println();
		
	}
	
	public static void winMsg(char player) {
		System.out.println(player + " won!");
	}
	
	public static void drawMsg() {
		System.out.println("It's a draw!");
	}

}
