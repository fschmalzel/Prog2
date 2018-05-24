package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.GameImplConsole;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.console.GameImplAsyncConsole;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.fx.FxUI;
import de.hsa.games.fatsquirrel.fx.GameImplFX;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application{

	private static BoardConfig boardConfig = new BoardConfig();
	private static Logger logger = Logger.getLogger(Launcher.class.getName());
	
	public static void main(String[] args) {
		logger.log(Level.INFO, "Launcher started!");
		if(args.length >= 1 && args[0].equalsIgnoreCase("ui=console")) {
			if (args.length >= 2 && args[1].equalsIgnoreCase("singleThread=true")) {
				logger.log(Level.FINE, "Synchrounos ConsoleUI started");
				(new GameImplConsole(boardConfig)).run();
			} else {
				logger.log(Level.FINE, "Asynchrounos ConsoleUI started");
				(new GameImplAsyncConsole(boardConfig)).run();
			}
		} else {
			logger.log(Level.FINE, "FXUI started");
			Application.launch(args);
		}
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = new GameImplFX(boardConfig, fxUI);
         
        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");
        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent evt) {
                System.exit(-1);     
            }
        });
        primaryStage.show();
        
        game.run();
	}

}
