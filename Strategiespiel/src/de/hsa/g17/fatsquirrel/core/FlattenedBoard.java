package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.BadBeast;
import de.hsa.g17.fatsquirrel.entities.BadPlant;
import de.hsa.g17.fatsquirrel.entities.GoodBeast;
import de.hsa.g17.fatsquirrel.entities.GoodPlant;
import de.hsa.g17.fatsquirrel.entities.MasterSquirrel;
import de.hsa.g17.fatsquirrel.entities.MiniSquirrel;
import de.hsa.g17.fatsquirrel.entities.Squirrel;

public class FlattenedBoard implements BoardView, EntityContext {

	private Entity[][] flatBoard;
	private Board board;
	
	public FlattenedBoard(Board board) {
		this.board = board;
		updateFlatBoardCompletely();
	}
	
	private void updateFlatBoardCompletely() {
		BoardConfig config = board.getConfig();
		Entity[][] newFlatBoard = new Entity[config.getSize().x()][config.getSize().y()];
		
		Entity[] entityArray = board.getEntitys();
		
		for (Entity e : entityArray) {
			int x = e.getXY().x();
			int y = e.getXY().y();
			
			if (x < config.getSize().x() && x >= 0 && y < config.getSize().y() && y >= 0) {
				newFlatBoard[x][y] = e;
			}
		}
		
		flatBoard = newFlatBoard;
	}

	@Override
	public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
		if(tryMoveSquirrel(masterSquirrel, moveDirection))
			return;
		
		Entity e = getEntity(masterSquirrel.getXY().add(moveDirection));
		switch(getEntityType(masterSquirrel.getXY().add(moveDirection))) {
		case MINI_SQUIRREL:
			if(masterSquirrel.isChild(e))
				masterSquirrel.updateEnergy(e.getEnergy());
			else
				masterSquirrel.updateEnergy(150);
			
			move(masterSquirrel, moveDirection);
			kill(e);
			break;
		default:
			break;
		}

	}
	
	@Override
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
		if(!tryMoveSquirrel(miniSquirrel, moveDirection)) {
			Entity e = getEntity(miniSquirrel.getXY().add(moveDirection));
			switch(getEntityType(miniSquirrel.getXY().add(moveDirection))) {
			case MASTER_SQUIRREL:
				MasterSquirrel masterSquirrel = (MasterSquirrel) e;
				if(masterSquirrel.isChild(miniSquirrel))
					masterSquirrel.updateEnergy(miniSquirrel.getEnergy());
				
				kill(miniSquirrel);
				break;
			case MINI_SQUIRREL:
				MiniSquirrel miniSquirrel2 = (MiniSquirrel) e;
				if(miniSquirrel.getMasterID() != miniSquirrel2.getMasterID()) {
					kill(miniSquirrel);
					kill(miniSquirrel2);
				}
				break;
			default:
				break;
			}
		}
		
		if(miniSquirrel.getEnergy() <= 0)
			kill(miniSquirrel);

	}
	
	private boolean tryMoveSquirrel(Squirrel squirrel, XY moveDirection) {
		Entity e = getEntity(squirrel.getXY().add(moveDirection));
		switch(getEntityType(squirrel.getXY().add(moveDirection))) {
		case EMPTY:
			move(squirrel, moveDirection);
			return true;
		case GOOD_BEAST:
		case GOOD_PLANT:
		case BAD_PLANT:
			move(squirrel, moveDirection);
			squirrel.updateEnergy(e.getEnergy());
			killAndReplace(e);
			return true;
		case BAD_BEAST:
			squirrel.updateEnergy(e.getEnergy());
			if(!((BadBeast) e).bites(this)) {
				move(squirrel, moveDirection);
			}
			return true;
		case WALL:
			squirrel.stun();
			squirrel.updateEnergy(e.getEnergy());
			return true;
		default:
			return false;
		}
	}
	
	@Override
	public void tryMove(GoodBeast goodBeast, XY moveDirection) {
		Entity e = getEntity(goodBeast.getXY().add(moveDirection));
		switch(getEntityType(goodBeast.getXY().add(moveDirection))) {
		case EMPTY:
			move(goodBeast, moveDirection);
			break;
		case MASTER_SQUIRREL:
		case MINI_SQUIRREL:
			e.updateEnergy(goodBeast.getEnergy());
			killAndReplace(goodBeast);
			break;
		default:
			return;
		}

	}

	@Override
	public void tryMove(BadBeast badBeast, XY moveDirection) {
		
		XY toGo = badBeast.getXY().add(moveDirection);
		
		Entity e = getEntity(toGo);
		switch(getEntityType(toGo)) {
		case EMPTY:
			move(badBeast, moveDirection);
			break;
		case MASTER_SQUIRREL:
		case MINI_SQUIRREL:
			e.updateEnergy(badBeast.getEnergy());
			if (e instanceof MiniSquirrel && e.getEnergy() <= 0) {
				kill(e);
				move(badBeast, moveDirection);
			}
			badBeast.bites(this);
			break;
		default:
			break;
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
	
	public boolean tryInsert(Entity e) {
		XY xy = e.getXY();
		if(flatBoard[xy.x()][xy.y()] != null)
			return false;
		board.insert(e);
		return true;
	}
	
	private void move(Entity entity, XY moveDirection) {
		XY xy = entity.getXY();
		flatBoard[xy.x()][xy.y()] = null;
		entity.move(moveDirection);
		xy = entity.getXY();
		flatBoard[xy.x()][xy.y()] = entity;
	}
	
	@Override
	public void kill(Entity entity) {
		XY xy = entity.getXY();
		if (flatBoard[xy.x()][xy.y()] == entity) {
			flatBoard[xy.x()][xy.y()] = null;
		}
		board.remove(entity);
	}
	
	@Override
	public void killAndReplace(Entity entity) {
		kill(entity);
		
		XY xy = XY.getRandomCoordinates(board.getConfig().getSize(), board.getEntitys());
		switch(entity.getEntityType()) {
		case GOOD_PLANT:
			entity = new GoodPlant(xy);
			break;
		case BAD_PLANT:
			entity = new BadPlant(xy);
			break;
		case GOOD_BEAST:
			entity = new GoodBeast(xy);
			break;
		case BAD_BEAST:
			entity = new BadBeast(xy);
			break;
		default:
			return;
		}
		
		board.insert(entity);
		flatBoard[xy.x()][xy.y()] = entity;
	}

	@Override
	public EntityType getEntityType(XY xy) {
		Entity e = flatBoard[xy.x()][xy.y()];
		if (e == null)
			return EntityType.EMPTY;
		return e.getEntityType();
	}
	
	private Entity getEntity(XY xy) {
		return flatBoard[xy.x()][xy.y()];
	}

	@Override
	public XY getSize() {
		return board.getConfig().getSize();
	}

}
