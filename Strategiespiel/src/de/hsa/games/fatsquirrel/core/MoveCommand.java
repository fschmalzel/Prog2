package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;

public class MoveCommand extends GameCommand {

	private XY xy;
	
	public MoveCommand(XY moveDirection) {
		super(Type.MOVE);
		this.xy = moveDirection;
	}
	
	public XY getMoveDirection() {
		return xy;
	}
	
}
