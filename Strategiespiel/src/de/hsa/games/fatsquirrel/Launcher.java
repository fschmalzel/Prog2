package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.GameImplConsole;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

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
	
	public static void main(String[] args) throws SecurityException, IOException {
		Handler handler = new ConsoleHandler();
		FileHandler fileHandler = new FileHandler();
		
		fileHandler.setLevel(Level.ALL);
		handler.setLevel(Level.ALL);

		handler.setFormatter(new SimpleFormatter());
		fileHandler.setFormatter(new XMLFormatter());
		
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);
		
		logger.addHandler(handler);
		logger.addHandler(fileHandler);
				
		logger.log(Level.INFO, "Launcher started!");
		
		if(args.length >= 1 && args[0].equalsIgnoreCase("ui=console")) {
			if (args.length >= 2 && args[1].equalsIgnoreCase("singleThread=true")) {
				logger.log(Level.FINER, "Synchrounos ConsoleUI started");
				(new GameImplConsole(boardConfig)).run();
			} else {
				logger.log(Level.FINER, "Asynchrounos ConsoleUI started");
				(new GameImplAsyncConsole(boardConfig)).run();
			}
		} else {
			logger.log(Level.FINER, "FXUI started");
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
        		Launcher.getLogger().info("Game ended!");
                System.exit(-1);     
            }
        });
        primaryStage.show();
        
        game.run();
	}
	
	public static Logger getLogger() {
		return logger;
	}

}
