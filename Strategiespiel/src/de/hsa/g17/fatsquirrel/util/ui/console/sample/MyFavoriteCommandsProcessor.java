package de.hsa.g17.fatsquirrel.util.ui.console.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import de.hsa.g17.fatsquirrel.util.ui.console.Command;
import de.hsa.g17.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.g17.fatsquirrel.util.ui.console.CommandTypeInfo;
import de.hsa.g17.fatsquirrel.util.ui.console.NoSuchCommandException;

public class MyFavoriteCommandsProcessor {
	private PrintStream outputStream = System.out;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	public void process() {
		CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(), inputReader, outputStream);
	       
		while (true) { // the loop over all commands with one input line for every command
			Command command;
			try {
				command = commandScanner.next();
			} catch (NoSuchCommandException e){
				e.printStackTrace(outputStream);
				continue;
			}

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
		    	for (int i = 0; i < (Integer) params[1]; i++) {
		    		outputStream.println((String) params[0]);
		    	}
		    	break;
		    default:
		    	break;
		    }
		}
	}
	
	private void help() {
		for(CommandTypeInfo cmd : MyFavoriteCommandType.values())
			System.out.println(cmd.getName() + "\t" + cmd.getHelpText());
	}
	
	public static void main(String[] args) {
		MyFavoriteCommandsProcessor cmdProc = new MyFavoriteCommandsProcessor();
		cmdProc.process();
	}


}
