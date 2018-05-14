package de.hsa.g17.fatsquirrel.core;

import java.util.Timer;
import java.util.TimerTask;

import de.hsa.g17.fatsquirrel.core.ui.console.ConsoleUI;
import de.hsa.g17.fatsquirrel.core.ui.console.FxUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application{

	private static BoardConfig boardConfig = new BoardConfig();
	
	public static void main(String[] args) {
		
		if (args.length >= 1)
			if (args[0].equalsIgnoreCase("singleThread=true")) {
				(new GameImpl(new ConsoleUI(true), boardConfig, true)).run();
				return;
			}
		
		Application.launch(args);
	}
	
	
	private static void startGame(Game game) {
		Timer t = new Timer();
		
		t.schedule(new TimerTask() {
		
			@Override
			public void run() {
				game.run();
			}
		}, 0);
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		
        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = new GameImpl(fxUI, boardConfig, false);
         
        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");
        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent evt) {
                System.exit(-1);     
            }
        });
        primaryStage.show();   
        
        startGame(game);		
	}

}
