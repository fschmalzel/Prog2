package de.hsa.games.fatsquirrel.console;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * Specifies a function in an interface as a valid command for the console.
 */
@Retention(RUNTIME)
public @interface AsCommand {
	String getName();
	String getHelpText();
}
