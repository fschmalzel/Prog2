package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.core.ui.ConsoleUI;
import de.hsa.g17.fatsquirrel.core.ui.ConsoleUIAsync;
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
				(new GameImpl(boardConfig, new ConsoleUI())).run();
			else {
				ConsoleUIAsync ui = new ConsoleUIAsync();
				(new GameImplAsync(boardConfig, ui)).run();
				ui.process();
			}
		} else
			Application.launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = new GameImplAsync(boardConfig, fxUI);
         
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
