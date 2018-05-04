package de.hsa.g17.fatsquirrel.core.ui.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import de.hsa.g17.fatsquirrel.core.BoardView;
import de.hsa.g17.fatsquirrel.core.MoveCommand;
import de.hsa.g17.fatsquirrel.core.UI;
import de.hsa.g17.fatsquirrel.core.XY;
import de.hsa.g17.fatsquirrel.util.ui.console.AsCommand;
import de.hsa.g17.fatsquirrel.util.ui.console.Command;
import de.hsa.g17.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.g17.fatsquirrel.util.ui.console.UniversalCommandProcessor;

public class ConsoleUI implements UI, GameCommands {

	private MoveCommand bufferedInput;
	
	@Override
	public MoveCommand getCommand() {
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		CommandScanner scanner = new UniversalCommandProcessor(GameCommands.class, this, inputReader, System.out).getScanner();
		do {
			Command cmd = scanner.next();
			cmd.execute();
		} while (bufferedInput == null);
		
		MoveCommand tmp = bufferedInput;
		bufferedInput = null;
		return tmp;
		
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

	@Override
	public void help() {
		Method[] methods = GameCommands.class.getDeclaredMethods();
		for (Method m : methods) {
			AsCommand ac = m.getAnnotation(AsCommand.class);
			if (ac != null)
				System.out.println(ac.getName() + '\t' + ac.getHelpText());
		}
	}

	@Override
	public void exit() {
		System.exit(0);
	}

	@Override
	public void left() {
		bufferedInput = new MoveCommand(new XY(-1, 0));
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void up() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void down() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void all() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void masterenergy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnmini(Integer energy, Integer xOffset, Integer yOffset) {
		// TODO Auto-generated method stub
		
	}

}
