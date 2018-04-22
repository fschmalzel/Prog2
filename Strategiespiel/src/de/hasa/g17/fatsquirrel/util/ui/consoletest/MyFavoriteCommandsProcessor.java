package de.hasa.g17.fatsquirrel.util.ui.consoletest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MyFavoriteCommandsProcessor {
	private PrintStream outputStream = System.out;
	private  BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	private Command command;
	
	public void process() {
		//TODO: Das hab ich nur kopiert
		CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(), inputReader);
	       
		while (true) { // the loop over all commands with one input line for every command
			String s = null;
			
			try {
				s = inputReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			command = commandScanner.next(s);
     //...

			Object[] params = command.getParams();

		    MyFavoriteCommandType commandType = (MyFavoriteCommandType) command.getCommandType();
		     
		    switch (commandType) {
		    case EXIT:
		    	System.exit(0);
		    case HELP: 
		    	help();
		        break;
		    case ADDI:
		    	break;
		    case ADDF:
		    	break;
		    case ECHO:
		    	break;
		    	//....
		    default:
		    	break;
		    }
		}
	}
	
	private void help() {
		
	}
	
	public static void main(String[] args) {
	
	}


}
