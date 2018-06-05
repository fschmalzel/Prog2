package console.better.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

public class CommandScanner {

	CommandType[] commandTypes;
	BufferedReader inputReader;
	OutputStream outputStream;
	
	public CommandScanner(CommandType[] commandTypes, BufferedReader inputReader, OutputStream outputStream) {
		this.inputReader = inputReader;
		this.outputStream = outputStream;
		this.commandTypes = commandTypes;
	}

	public Command next() {
		// Erstmal müssen wir eine Zeile userinput einlesen die dann
		// als Befehl interpretiert wird.
		String input;
		try {
			input = inputReader.readLine();
		} catch (IOException e) {
			// Falls ein Fehler beim Einlesen passiert, fangen wir
			// diesen ab, und werfen eine ScanException
			throw new ScanException();
		}

		// Hier wird der name des Befehls (Bsp.: help) extrahiert
		// Also anhand des indexes von ' ' das erste "Wort" abgetrennt
		// Falls kein ' ' vorhanden ist wird der input direkt hergenommen, da
		// ja nur ein Wort vorhanden ist.
		// Um zu vermeiden das dieses Wort anschließend als Parameter verwertet
		// wird müssen wir input leeren, da wir alles abgegriffen haben
		int split = input.indexOf(' ');

		String name;
		if (split != -1) {
			name = input.substring(0, split);
			input = input.substring(split + 1);
		} else {
			name = input;
			input = "";
		}

		CommandType cmd = null;

		// Jetzt nachdem wir den Namen des befehls haben
		// Können wir alle bekannten Befehle durchlaufen und
		// überprüfen ob wir den Befehl kennen und wenn dies
		// der Fall ist zwischenspeichern um gleich die Parameter
		// abzuholen
		for (CommandType commandType : commandTypes) {
			if (commandType.getName().equalsIgnoreCase(name)) {
				cmd = commandType;
				break;
			}
		}

		// Falls kein Befehl gefunden wurde, werfen wir eine
		// ScanException
		if (cmd == null)
			throw new NoSuchCommandException();

		Object[] params = new Object[cmd.getParamTypes().length];

		// Jetzt gehen wir die Liste an Parametern durch
		for (int i = 0; i < cmd.getParamTypes().length; i++) {

			// Holen uns den jeweiligen Wert des Parameters vom
			// input wie mit dem namen vorher
			split = input.indexOf(' ');
			String param;
			if (split != -1) {
				param = input.substring(0, split);
				input = input.substring(split + 1);
			} else {
				param = input;
				input = "";
			}

			// Suchen uns jetzt einen geeigneten Konstruktor der jeweiligen Klasse
			// Also einen Konstruktor der einen String annimmt.
			// Und erstellen mit diesem Konstruktur eine Objekt der Klasse
			// Mit unserem param String und speichern diesen in unserem
			// letztlichen Parameter Array

			Class<?> paramType = cmd.getParamTypes()[i];
			try {
				params[i] = paramType.getConstructor(String.class).newInstance(param);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				// Falls etwas schief geht, gibt es einfach eine ScanException
				// Falls wir wollen können wir diese Exceptions spezifischer
				// gestalten, also eine Exception die von ScanException erbt,
				// und einen Fall beschreibt bei dem zu wenige Parameter da sind.
				// TODO Exceptions genauer machen
				throw new ScanException();
			}

		}

		// Zum Schluss können wir einen neuen Command mit den CommandTypeInfo und
		// den erzeugten Parametern erstellen und zurückliefern
		return new Command(cmd, params);
	}

}
