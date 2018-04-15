package de.hsa.g17.fatsquirrel.core;

public interface BoardView {
	EntityType getEntityType(XY xy);
	XY getSize();
}
