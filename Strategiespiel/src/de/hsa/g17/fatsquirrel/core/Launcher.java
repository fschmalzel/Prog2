package de.hsa.g17.fatsquirrel.core;

import java.util.Timer;
import java.util.TimerTask;

import de.hsa.g17.fatsquirrel.bots.random.RandomBotControllerFactory;
import de.hsa.g17.fatsquirrel.core.ui.ConsoleUI;
import de.hsa.g17.fatsquirrel.core.ui.FxUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application{

	private static BoardConfig boardConfig = new BoardConfig();
	
	public static void main(String[] args) {
		if(args.length >= 1 && args[0].equalsIgnoreCase("ui=console")) {
			if (args.length >= 2 && args[1].equalsIgnoreCase("singleThread=true"))
				(new SynGameImpl(boardConfig, new ConsoleUI())).synchronizedRun();
			else {
				ConsoleUI ui = new ConsoleUI();
				startGame(new GameImpl(ui, boardConfig), ui);
			}
		} else
			Application.launch(args);
		
	}
	
	
	private static void startGame(Game game) {
		Timer t = new Timer();
		
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				game.asynchronizedRun();
			}
		}, 0);
		
	}
	
	private static void startGame(Game game, ConsoleUI ui) {
		startGame(game);
		ui.process();
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		
        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = new GameImpl(fxUI, boardConfig, false, new RandomBotControllerFactory());
         
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
