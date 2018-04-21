package de.hasa.g17.fatsquirrel.util.ui.consoletest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {
	private CommandTypeInfo[] commandTypeInfos;
	private BufferedReader inputReader;
	private PrintStream outputStream;
	private Command command;
	
	public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader) {
		this.commandTypeInfos = commandTypes;
		this.inputReader = inputReader;
	}	
	
	public CommandTypeInfo[] getCommandTypeInfos() {
		return commandTypeInfos;
	}
	public BufferedReader getInputReader() {
		return inputReader;
	}
	public PrintStream getOutputStream() {
		return outputStream;
	}
	public Command next() {
		String nameCommand = null;
		
		//TODO: Eingabe lesen und alle bis zum Identifizierer lesen (\n & \r hab ich zur sicherheit reingetan)
		try {
			while(inputReader.read() != ' ' || inputReader.read() != '\n' || inputReader.read() != '\r')
				nameCommand += (char)inputReader.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//TODO: Mit den identifizierer erstell ich ein neuen command mit den paramtypen und commandtype (bin mir unsicher ob es mit den enum array so funktioniert)
		switch(nameCommand) {
		case "help":
			command = new Command(commandTypeInfos[0],commandTypeInfos[0].getParamTypes());
			break;
		case "exit":
			command = new Command(commandTypeInfos[1],commandTypeInfos[1].getParamTypes());
			break;
		case "addi":
			command = new Command(commandTypeInfos[2],commandTypeInfos[2].getParamTypes());
			break;
		case "addf":
			command = new Command(commandTypeInfos[3],commandTypeInfos[3].getParamTypes());
			break;
		case "echo":
			command = new Command(commandTypeInfos[4],commandTypeInfos[4].getParamTypes());
			break;
		default:
			return null;
		}
		
		return command;
	}
}
