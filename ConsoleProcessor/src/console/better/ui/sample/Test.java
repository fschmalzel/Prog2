package console.better.ui.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import console.better.ui.UniversalCommandProcessor;

public class Test {

	public static void main(String[] args) {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		
		UniversalCommandProcessor uniProcessor = new UniversalCommandProcessor(UserActions.class, new UserActionsImpl(), inputReader, System.out);
		uniProcessor.process();
	}

}
