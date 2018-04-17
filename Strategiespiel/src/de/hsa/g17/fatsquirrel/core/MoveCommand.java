package de.hsa.g17.fatsquirrel.core;

public class MoveCommand {
	
	private XY moveDirection;
	
	public MoveCommand(XY moveDirection) {
		this.moveDirection = moveDirection;
	}
	
	public XY getMoveDirection() {
		return moveDirection;
	}
	
}
