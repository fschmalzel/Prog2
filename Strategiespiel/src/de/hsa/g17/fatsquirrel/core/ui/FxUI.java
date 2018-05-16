package de.hsa.g17.fatsquirrel.core.ui;

import de.hsa.g17.fatsquirrel.core.BoardView;
import de.hsa.g17.fatsquirrel.core.GameCommand;
import de.hsa.g17.fatsquirrel.core.MoveCommand;
import de.hsa.g17.fatsquirrel.core.UI;
import de.hsa.g17.fatsquirrel.core.XY;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FxUI extends Scene implements UI {

	private static final int CELL_SIZE = 50;
	private Canvas boardCanvas;
	private Label msgLabel;
	private GameCommand gameCommand;

	public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
		super(parent);
		this.boardCanvas = boardCanvas;
		this.msgLabel = msgLabel;
	}

	public static FxUI createInstance(XY boardSize) {
		Canvas boardCanvas = new Canvas(boardSize.x() * CELL_SIZE, boardSize.y() * CELL_SIZE);
		Label statusLabel = new Label();
		VBox top = new VBox();
		top.getChildren().add(boardCanvas);
		top.getChildren().add(statusLabel);
		statusLabel.setText("sample");
		final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);
		fxUI.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
				case UP:
					fxUI.setCommand(new MoveCommand(new XY(0,-1)));
					break;
				case DOWN:
					fxUI.setCommand(new MoveCommand(new XY(0,1)));
					break;
				case LEFT:
					fxUI.setCommand(new MoveCommand(new XY(-1,0)));
					break;
				case RIGHT:
					fxUI.setCommand(new MoveCommand(new XY(1,0)));
					break;
				case I:
					fxUI.setCommand(new GameCommand(GameCommand.Type.MASTERENERGY));
					break;
				default:
					break;
				}
			}
		});
		return fxUI;
	}

	@Override
	public void render(final BoardView view) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				repaintBoardCanvas(view);
			}
		});
	}

	private void repaintBoardCanvas(BoardView view) {
		GraphicsContext gc = boardCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
		XY viewSize = view.getSize();

		for (int y = 0; y < viewSize.y(); y++)
			for (int x = 0; x < viewSize.x(); x++) {
				switch (view.getEntityType(new XY(x, y))) {
				case BAD_BEAST:
					gc.setFill(Color.RED);
					gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
					break;
				case BAD_PLANT:
					gc.setFill(Color.ORANGE);
					gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
					break;
				case EMPTY:
					break;
				case GOOD_BEAST:
					gc.setFill(Color.GREEN);
					gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
					break;
				case GOOD_PLANT:
					gc.setFill(Color.GREENYELLOW);
					gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
					break;
				case MASTER_SQUIRREL:
					gc.setFill(Color.BLUE);
					gc.fillRoundRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, CELL_SIZE / 2, CELL_SIZE / 2);
					break;
				case MINI_SQUIRREL:
					gc.setFill(Color.AQUAMARINE);
					gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
					break;
				case UNDEFINED:
					break;
				case WALL:
					gc.setFill(Color.GREY);
					gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
					break;
				default:
					break;
				}
			}

	}

	@Override
	public void message(final String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				msgLabel.setText(msg);
			}
		});
	}

	@Override
	public GameCommand getCommandUnsyn() {
		GameCommand tmp = gameCommand;
		gameCommand = null;
		return tmp;
	}
	
	private void setCommand(GameCommand gameCommand) {
		this.gameCommand = gameCommand;
	}

	@Override
	public GameCommand getCommand() {
		// TODO Auto-generated method stub
		return null;
	}
}
