package de.hsa.g17.fatsquirrel.core;

import java.util.Timer;
import java.util.TimerTask;

import de.hsa.g17.fatsquirrel.core.ui.console.FxUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application{

	BoardConfig boardConfig = new BoardConfig();
	static Game game;
	
	public static void main(String[] args) {
		
		if (args.length >= 1)
			if (args[0].equalsIgnoreCase("singleThread=true")) {
				(new GameImpl(true)).run();
				return;
			}
		game = new GameImpl(false);
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
		
//		game.ui.process();
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		
        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = Launcher.game;
         
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
