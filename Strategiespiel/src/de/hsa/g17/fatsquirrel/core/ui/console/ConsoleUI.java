package de.hsa.g17.fatsquirrel.core.ui.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.hsa.g17.fatsquirrel.core.BoardView;
import de.hsa.g17.fatsquirrel.core.MoveCommand;
import de.hsa.g17.fatsquirrel.core.UI;
import de.hsa.g17.fatsquirrel.core.XY;
import de.hsa.g17.fatsquirrel.util.ui.console.Command;
import de.hsa.g17.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.g17.fatsquirrel.util.ui.console.CommandTypeInfo;

public class ConsoleUI implements UI {

	@Override
	public MoveCommand getCommand() {
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

		CommandScanner scanner = new CommandScanner(GameCommandType.values(), inputReader, System.out);
		
		Command currcommand = scanner.next();
		switch ((GameCommandType) currcommand.getCommandType()) {
		case LEFT:
			return new MoveCommand(new XY(-1, 0));
		case RIGHT:
			return new MoveCommand(new XY(1, 0));
		case UP:
			return new MoveCommand(new XY(0, -1));
		case DOWN:
			return new MoveCommand(new XY(0, 1));
		case EXIT:
			System.exit(0);
		case HELP:
			for (CommandTypeInfo cmd : GameCommandType.values())
				System.out.println(cmd.getName() + "\t" + cmd.getHelpText());
			return null;
		case MASTER_ENERGY:
			
			return null;
		case SPAWN_MINI:

			return null;
		case ALL:

			return null;
		default:
			return null;
		}
		
	}

	@Override
	public void render(BoardView view) {
		for (int y = 0; y < view.getSize().y(); y++) {
			for (int x = 0; x < view.getSize().x(); x++) {
				char c;
				switch(view.getEntityType(new XY(x, y))) {
				case GOOD_BEAST:
					c = 'G';
					break;
				case BAD_BEAST:
					c = 'B';
					break;
				case GOOD_PLANT:
					c = 'g';
					break;
				case BAD_PLANT:
					c = 'b';
					break;
				case MASTER_SQUIRREL:
					c = 'M';
					break;
				case MINI_SQUIRREL:
					c = 'm';
					break;
				case WALL:
					c = '#';
					break;
				case UNDEFINED:
					c = '?';
					break;
				case EMPTY:
				default:
					c = ' ';
					break;
				}
				System.out.print(c);
			}
			System.out.println();
		}

	}

}
