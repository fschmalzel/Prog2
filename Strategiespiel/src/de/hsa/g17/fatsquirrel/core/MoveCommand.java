package de.hsa.g17.fatsquirrel.core;

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
