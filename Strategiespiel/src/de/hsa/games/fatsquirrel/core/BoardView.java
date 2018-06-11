package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;

public interface BoardView {
	/**
	 * @param xy The position to be checked
	 * @return Returns the {@link EntityType} of the {@link Entity} at the specified position.
	 */
	EntityType getEntityType(XY xy);
	/**
	 * @return The size of the board.
	 */
	XY getSize();
}
