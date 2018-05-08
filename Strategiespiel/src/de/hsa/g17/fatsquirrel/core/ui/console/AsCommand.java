package de.hsa.g17.fatsquirrel.core.ui.console;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface AsCommand {
	String getName();
	String getHelpText();
}
