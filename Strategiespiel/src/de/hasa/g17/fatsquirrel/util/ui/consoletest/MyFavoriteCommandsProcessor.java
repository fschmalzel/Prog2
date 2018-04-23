package de.hasa.g17.fatsquirrel.util.ui.consoletest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MyFavoriteCommandsProcessor {
	private PrintStream outputStream = System.out;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	public void process() {
		CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(), inputReader, outputStream);
	       
		while (true) { // the loop over all commands with one input line for every command
			Command command;
			
			command = commandScanner.next();

			Object[] params = command.getParams();

		    MyFavoriteCommandType commandType = (MyFavoriteCommandType) command.getCommandType();
		     
		    switch (commandType) {
		    case EXIT:
		    	System.exit(0);
		    case HELP: 
		    	help();
		        break;
		    case ADDI:
		    	outputStream.println((Integer) params[0] + (Integer) params[1]);
		    	break;
		    case ADDF:
		    	outputStream.println((Float) params[0] + (Float) params[1]);
		    	break;
		    case ECHO:
		    	break;
		    default:
		    	break;
		    }
		}
	}
	
	private void help() {
		MyFavoriteCommandType.values();
		
		for(CommandTypeInfo cmd : MyFavoriteCommandType.values()) {
			System.out.println(cmd.getName() + "\t" + cmd.getHelpText());
		}
	}
	
	public static void main(String[] args) {
		MyFavoriteCommandsProcessor cmdProc = new MyFavoriteCommandsProcessor();
		cmdProc.process();
	}


}
