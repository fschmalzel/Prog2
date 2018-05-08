package de.hsa.g17.fatsquirrel.core.ui.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import de.hsa.g17.fatsquirrel.core.BoardView;
import de.hsa.g17.fatsquirrel.core.GameCommand;
import de.hsa.g17.fatsquirrel.core.MoveCommand;
import de.hsa.g17.fatsquirrel.core.SpawnCommand;
import de.hsa.g17.fatsquirrel.core.UI;
import de.hsa.g17.fatsquirrel.core.XY;

public class ConsoleUI implements UI, GameCommands {
	
	@Override
	public GameCommand getCommand() {
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		CommandScanner scanner = new UniversalCommandProcessor(GameCommands.class, this, inputReader, System.out).getScanner();
		do {
			Command cmd;
			
			try {
				cmd = scanner.next();
			} catch (NoSuchCommandException e) {
				System.out.println("Befehl \"" +  e.getMessage() + "\" nicht bekannt!");
				continue;
			}
			
			Object result = cmd.execute();
			if (result instanceof GameCommand && result != null)
				return (GameCommand) result;
		} while (true);
		
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
		System.out.println("Bye bye!");
		System.exit(0);
	}

	@Override
	public MoveCommand left() {
		return new MoveCommand(new XY(-1, 0));
	}

	@Override
	public MoveCommand right() {
		return new MoveCommand(new XY(1, 0));
	}

	@Override
	public MoveCommand up() {
		return new MoveCommand(new XY(0, -1));
	}

	@Override
	public MoveCommand down() {
		return new MoveCommand(new XY(0, 1));
	}

	@Override
	public void all() {
		
	}

	@Override
	public GameCommand masterenergy() {
		return new GameCommand(GameCommand.Type.MASTERENERGY);
	}

	@Override
	public SpawnCommand spawnmini(Integer energy, Integer xOffset, Integer yOffset) {
		return new SpawnCommand(energy, new XY(xOffset, -yOffset));
	}

}
