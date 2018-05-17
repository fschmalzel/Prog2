package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.GameImplConsole;
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
	
	public static void main(String[] args) {
		if(args.length >= 1 && args[0].equalsIgnoreCase("ui=console")) {
			if (args.length >= 2 && args[1].equalsIgnoreCase("singleThread=true"))
				(new GameImplConsole(boardConfig)).run();
			else {
				(new GameImplAsyncConsole(boardConfig)).run();
			}
		} else
			Application.launch(args);
		
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
