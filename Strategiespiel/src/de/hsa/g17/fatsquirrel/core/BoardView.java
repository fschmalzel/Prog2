package de.hsa.g17.fatsquirrel.core;

public interface BoardView {
	EntityType getEntityType(XY xy);
	EntityType getEntityType(int x, int y);
	XY getSize();
}
