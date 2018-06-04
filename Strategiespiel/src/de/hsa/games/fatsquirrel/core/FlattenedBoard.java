package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.BadPlant;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.entities.GoodPlant;
import de.hsa.games.fatsquirrel.entities.MasterSquirrel;
import de.hsa.games.fatsquirrel.entities.MiniSquirrel;
import de.hsa.games.fatsquirrel.entities.Squirrel;
import de.hsa.games.fatsquirrel.entities.Wall;
import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class FlattenedBoard implements BoardView, EntityContext {

	private Entity[][] flatBoard;
	private Board board;
	
	public FlattenedBoard(Board board) {
		this.board = board;
		updateFlatBoardCompletely();
	}
	
	private void updateFlatBoardCompletely() {
		BoardConfig config = board.getConfig();
		Entity[][] newFlatBoard = new Entity[config.getSize().x][config.getSize().y];
		
		Entity[] entityArray = board.getEntitys();
		
		for (Entity e : entityArray) {
			int x = e.getXY().x;
			int y = e.getXY().y;
			
			if (x < config.getSize().x && x >= 0 && y < config.getSize().y && y >= 0) {
				newFlatBoard[x][y] = e;
			}
		}

		flatBoard = newFlatBoard;
	}

	@Override
	public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
		if(tryMoveSquirrel(masterSquirrel, moveDirection))
			return;
		
		Entity e = getEntity(masterSquirrel.getXY().plus(moveDirection));
		switch(getEntityType(masterSquirrel.getXY().plus(moveDirection))) {
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
			Entity e = getEntity(miniSquirrel.getXY().plus(moveDirection));
			switch(getEntityType(miniSquirrel.getXY().plus(moveDirection))) {
			case MASTER_SQUIRREL:
				MasterSquirrel masterSquirrel = (MasterSquirrel) e;
				if(masterSquirrel.isChild(miniSquirrel))
					masterSquirrel.updateEnergy(miniSquirrel.getEnergy());
				
				kill(miniSquirrel);
				break;
			case MINI_SQUIRREL:
				MiniSquirrel miniSquirrel2 = (MiniSquirrel) e;
				if (!miniSquirrel.hasSameMaster(miniSquirrel2)) {
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
		Entity e = getEntity(squirrel.getXY().plus(moveDirection));
		switch(getEntityType(squirrel.getXY().plus(moveDirection))) {
		case NONE:
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
		Entity e = getEntity(goodBeast.getXY().plus(moveDirection));
		switch(getEntityType(goodBeast.getXY().plus(moveDirection))) {
		case NONE:
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
		
		XY toGo = badBeast.getXY().plus(moveDirection);
		
		Entity e = getEntity(toGo);
		switch(getEntityType(toGo)) {
		case NONE:
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
		
		int startX = pos.x - 6;
		if (startX < 0)
			startX = 0;
		
		int endX = pos.x + 6;
		if (endX >= size.x)
			endX = size.x-1;
		
		
		int startY = pos.y - 6;
		if (startY < 0)
			startY = 0;
		
		int endY = pos.y + 6;
		if (endY >= size.y)
			endY = size.y-1;
		
		Squirrel s = null;
		
		for(int x = startX; x <= endX; x++) {
			for(int y = startY; y <= endY; y++) {
				Entity e = flatBoard[x][y];
				if (e != null && e instanceof Squirrel) {
					if (s == null) {
						s = (Squirrel) e;
					} else if (pos.distanceFrom(e.getXY()) < pos.distanceFrom(s.getXY())){
						s = (Squirrel) e;
					}
				}
				
			}
		}
		
		return s;
	}
	
	public boolean tryInsert(Entity e) {
		XY xy = e.getXY();
		if(flatBoard[xy.x][xy.y] != null)
			return false;
		board.insert(e);
		return true;
	}
	
	@Override
	public void implode(MiniSquirrel m, int impactRadius) {
		if(!(impactRadius >= 2 && impactRadius <= 10))
			return;

		double impactArea = impactRadius * impactRadius * Math.PI;
		int collectedEnergy = 0;
		
		XY xy = m.getXY();
		
		for (int x = xy.x - impactRadius; x < xy.x + impactRadius; x++)
			for (int y = xy.y - impactRadius; y < xy.y + impactRadius; y++) {
				XY temp = new XY(x, y);

				double distance = xy.distanceFrom(temp);
				
				if (distance > impactRadius)
					continue;
				
				Entity e = getEntity(xy);
				if (e == null)
					continue;
				
				if (e instanceof MasterSquirrel && ((MasterSquirrel) e).isChild(m))
					continue;
				else if (m.hasSameMaster(e))
					continue;
				else if (e instanceof Wall)
					continue;
				
				int energyLoss = (int) (200 * (m.getEnergy() / impactArea) * (1 - distance / impactRadius));
				
				
				
				if (Math.abs(e.getEnergy()) < -energyLoss)
					energyLoss = -Math.abs(e.getEnergy());
				
				switch(e.getEntityType()) {
				case BAD_BEAST:
				case BAD_PLANT:
					e.updateEnergy(-energyLoss);
					if (e.getEnergy() >= 0)
						killAndReplace(e);
					break;
				case MASTER_SQUIRREL:
					e.updateEnergy(energyLoss);
					break;
				case GOOD_PLANT:
				case GOOD_BEAST:
					e.updateEnergy(energyLoss);
					if (e.getEnergy() <= 0)
						killAndReplace(e);
					break;
				default:
					continue;
				}
				
				collectedEnergy -= energyLoss;
				
			}

		m.getMaster().updateEnergy(collectedEnergy);
		kill(m);
	}
	
	private void move(Entity entity, XY moveDirection) {
		
		if (XY.ZERO_ZERO.distanceFrom(moveDirection) >= 2)
			return;
		
		XY xy = entity.getXY();
		flatBoard[xy.x][xy.y] = null;
		entity.move(moveDirection);
		xy = entity.getXY();
		flatBoard[xy.x][xy.y] = entity;
	}
	
	@Override
	public void kill(Entity entity) {
		XY xy = entity.getXY();
		if (flatBoard[xy.x][xy.y] == entity) {
			flatBoard[xy.x][xy.y] = null;
		}
		board.remove(entity);
	}
	
	@Override
	public void killAndReplace(Entity entity) {
		kill(entity);
		
		XY xy = XYsupport.getRandomCoordinates(board);
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
		flatBoard[xy.x][xy.y] = entity;
	}

	@Override
	public EntityType getEntityType(XY xy) {
		Entity e;
		
		try {
			e = flatBoard[xy.x][xy.y];
		} catch (ArrayIndexOutOfBoundsException exception) {
			e = null;
		}
		
		if (e == null)
			return EntityType.NONE;
		return e.getEntityType();
	}
	
	@Override
	public Entity getEntity(XY xy) {
		return flatBoard[xy.x][xy.y];
	}

	@Override
	public XY getSize() {
		return board.getConfig().getSize();
	}
	
	@Override
	public MasterSquirrel getMaster(MiniSquirrel s) {
		for ( Entity e : board.getEntitys()) {
			if (e instanceof MasterSquirrel && ((MasterSquirrel) e).isChild(s))
				return (MasterSquirrel) e;
		}
		return null;
	}

}
