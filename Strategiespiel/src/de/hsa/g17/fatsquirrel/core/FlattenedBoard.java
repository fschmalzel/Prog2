package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.BadBeast;
import de.hsa.g17.fatsquirrel.entities.BadPlant;
import de.hsa.g17.fatsquirrel.entities.GoodBeast;
import de.hsa.g17.fatsquirrel.entities.GoodPlant;
import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;
import de.hsa.g17.fatsquirrel.entities.MiniSquirrel;
import de.hsa.g17.fatsquirrel.entities.Squirrel;
import de.hsa.g17.fatsquirrel.entities.Wall;

public class FlattenedBoard implements BoardView, EntityContext {
	
	Board board;
	Entity[][] flatBoard;
	
	public FlattenedBoard(Board board) {
		this.board = board;
		flatBoard = board.flatten();
	}

	@Override
	public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
		if (tryMoveSquirrel(masterSquirrel, moveDirection))
			return;
		
		Entity e = getCollidingEntity(masterSquirrel, moveDirection);
		
		switch (e.getEntityType()) {
		case BAD_BEAST:
			masterSquirrel.updateEnergy(e.getEnergy());
			((BadBeast) e).bites(this);
			// TODO check if badbeast died and if it did, then move there
			return;
		case MASTER_SQUIRREL:
			// Nothing happens
			return;
		case MINI_SQUIRREL:
			MiniSquirrel miniSquirrel = (MiniSquirrel) e;
			
			if (masterSquirrel.isChild(miniSquirrel))
				masterSquirrel.updateEnergy(miniSquirrel.getEnergy());
			else
				masterSquirrel.updateEnergy(150);
			
			masterSquirrel.move(moveDirection);
			kill(miniSquirrel);
			return;
		default:
			// Should never be reached
			return;
		}
		
	}

	
	@Override
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
		if (tryMoveSquirrel(miniSquirrel, moveDirection))
			return;
		
		Entity e = getCollidingEntity(miniSquirrel, moveDirection);
		
		switch (e.getEntityType()) {
		case BAD_BEAST:
			miniSquirrel.updateEnergy(e.getEnergy(), this);
			((BadBeast) e).bites(this);
			// TODO check if badbeast died and if it did, then move there
			return;
		case MASTER_SQUIRREL:
			MasterSquirrel masterSquirrel = (MasterSquirrel) e;
			
			if (masterSquirrel.isChild(miniSquirrel))
				masterSquirrel.updateEnergy(miniSquirrel.getEnergy());
			
			kill(miniSquirrel);
			return;
		case MINI_SQUIRREL:
			MiniSquirrel miniSquirrel2 = (MiniSquirrel) e;
			
			if (miniSquirrel.getMasterID() != miniSquirrel2.getMasterID()) {
				kill(miniSquirrel);
			}
			return;
			
		default:
			// Should never be reached
			return;
		}
		

	}

	private boolean tryMoveSquirrel(Squirrel squirrel, XY moveDirection) {
		Entity e = getCollidingEntity(squirrel, moveDirection);
		
		switch(e == null ? EntityType.UNDEFINED : e.getEntityType()) {
			case BAD_PLANT:
			case GOOD_PLANT:
			case GOOD_BEAST:
				squirrel.move(moveDirection);
				squirrel.updateEnergy(e.getEnergy());
				killAndReplace(e);
				return true;
			case WALL:
				squirrel.stun();
				squirrel.updateEnergy(e.getEnergy());
				return true;
			case UNDEFINED:
				squirrel.move(moveDirection);
				update();
				return true;
			default:
				return false;
			}
	}
	
	@Override
	public void tryMove(GoodBeast goodBeast, XY moveDirection) {
		Entity e = getCollidingEntity(goodBeast, moveDirection);
		
		if (e == null) {
			goodBeast.move(moveDirection);
			update();
			return;
		}
		
		if (e instanceof Wall)
			return;
		
		if (e instanceof Squirrel) {
			e.updateEnergy(goodBeast.getEnergy());
			killAndReplace(goodBeast);
		}
		
	}

	@Override
	public void tryMove(BadBeast badBeast, XY moveDirection) {
		Entity e = getCollidingEntity(badBeast, moveDirection);
	
		if (e == null) {
			badBeast.move(moveDirection);
			update();
			return;
		}
		
		if (e instanceof Wall)
			return;
		
		if (e instanceof Squirrel) {
			if (e instanceof MiniSquirrel)
				((MiniSquirrel) e).updateEnergy(badBeast.getEnergy(), this);
			else if (e instanceof MasterSquirrel)
				e.updateEnergy(badBeast.getEnergy());

			// TODO check if minisquirrel died and if it did, then move there
			badBeast.bites(this);
		}

	}

	@Override
	public Squirrel nearestSquirrel(XY pos) {
		XY size = getSize();
		
		int startX = pos.x() - 6;
		if (startX < 0)
			startX = 0;
		
		int endX = pos.x() + 6;
		if (endX >= size.x())
			endX = size.x()-1;
		
		
		int startY = pos.y() - 6;
		if (startY < 0)
			startY = 0;
		
		int endY = pos.y() + 6;
		if (endY >= size.y())
			endY = size.y()-1;
		
		Squirrel s = null;
		
		for(int x = startX; x <= endX; x++) {
			for(int y = startY; y <= endY; y++) {
				Entity e = flatBoard[x][y];
				if (e != null && e instanceof Squirrel) {
					if (s == null) {
						s = (Squirrel) e;
					} else if (pos.distance(e.getXY()) < pos.distance(s.getXY())){
						s = (Squirrel) e;
					}
				}
				
			}
		}
		
		return s;
	}

	@Override
	public void kill(Entity entity) {
		board.remove(entity);
		update();
	}

	@Override
	public void killAndReplace(Entity entity) {
		board.remove(entity);
		
		if (entity instanceof GoodPlant)
			entity = new GoodPlant(board);
		else if (entity instanceof BadPlant)
			entity = new BadPlant(board);
		else if (entity instanceof GoodBeast)
			entity = new GoodBeast(board);
		else
			entity = new BadBeast(board);
		
		board.insert(entity);
		update();
	}
	
	private void update() {
		flatBoard = board.flatten();
	}

	@Override
	public EntityType getEntityType(XY xy) {
		Entity e = flatBoard[xy.x()][xy.y()];
		if (e == null)
			return EntityType.UNDEFINED;
		return e.getEntityType();
	}

	@Override
	public XY getSize() {
		return board.getConfig().getSize();
	}
	
	private Entity getCollidingEntity(Entity entity, XY moveDirection) {
		XY newCoord = entity.getXY().add(moveDirection);
		Entity e = flatBoard[newCoord.x()][newCoord.y()];
		return e;
	}

}
