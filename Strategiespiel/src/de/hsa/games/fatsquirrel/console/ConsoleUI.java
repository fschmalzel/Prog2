package de.hsa.games.fatsquirrel.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.GameCommand;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.core.SpawnCommand;
import de.hsa.games.fatsquirrel.util.XY;

public class ConsoleUI implements UI, CommandTypes {
	
	private CommandScanner scanner;
	
	public ConsoleUI() {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		scanner = new UniversalCommandProcessor(CommandTypes.class, this, inputReader, System.out).getScanner();
	
	}
	
	@Override
	public GameCommand getCommand() {
		Command cmd;
		
		try {
			cmd = scanner.next();
		} catch (NoSuchCommandException e) {
			System.out.println("Befehl \"" +  e.getMessage() + "\" nicht bekannt!");
			return null;
		}
		
		Object result = cmd.execute();
		if (result instanceof GameCommand && result != null)
			return (GameCommand) result;
		return null;
	}

	@Override
	public void render(BoardView view) {
		for (int y = 0; y < view.getSize().y; y++) {
			for (int x = 0; x < view.getSize().x; x++) {
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
				case NONE:
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
		Method[] methods = CommandTypes.class.getDeclaredMethods();
		for (Method m : methods) {
			AsCommand ac = m.getAnnotation(AsCommand.class);
			if (ac != null)
				System.out.println(ac.getName() + '\t' + ac.getHelpText());
		}
	}

	@Override
	public void exit() {
		Launcher.getLogger().info("Game ended!");
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
	public GameCommand all() {
		return new GameCommand(GameCommand.Type.ALL);
	}

	@Override
	public GameCommand masterenergy() {
		return new GameCommand(GameCommand.Type.MASTERENERGY);
	}

	@Override
	public SpawnCommand spawnmini(Integer energy, Integer xOffset, Integer yOffset) {
		return new SpawnCommand(energy, new XY(xOffset, -yOffset));
	}

	@Override
	public void message(String msg) {
		System.out.println(msg);
	}

}
