package de.hsa.g17.fatsquirrel.core;

import de.hasa.g17.fatsquirrel.util.ui.consoletest.CommandTypeInfo;

public enum GameCommandType implements CommandTypeInfo{
	HELP, EXIT, LEFT, RIGHT, UP, DOWN, ALL, MASTER_ENERGY, SPAWN_MINI;

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?>[] getParamTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
