package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.GameImplConsole;

import java.io.IOException;
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

public class Launcher extends Application {

	private static BoardConfig boardConfig;
	private static Logger logger = Logger.getLogger(Launcher.class.getName());

	public static void main(String[] args) throws SecurityException, IOException {
		logger.log(Level.INFO, "Launcher started!");
		
		try {
			boardConfig = new BoardConfig("exampleBoard.json");
		} catch(IOException e) {
			logger.warning("Error loading config! Using default values!");
			boardConfig = new BoardConfig();
		}
		
		if (args.length >= 1 && args[0].equalsIgnoreCase("ui=console")) {
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
				logger.info("Game ended!");
				System.exit(-1);
			}
		});
		primaryStage.show();

		game.run();
	}

}
