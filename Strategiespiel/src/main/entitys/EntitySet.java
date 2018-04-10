package main.entitys;

public class EntitySet {

	Entity[] entityArray;
	private static final int DEFAULT_SIZE = 50;
	
	public EntitySet(int size) {
		entityArray = new Entity[size];
	}
	
	public EntitySet() {
		this(DEFAULT_SIZE);
	}

	public void insert(Entity e) {
		
		// Stelle an der eingefügt wird
		int index = -1;
		
		for(int i = 0; i < entityArray.length; i++)
			if(entityArray[i] == e)
				// Falls das Objekt schon im Array vorhanden ist,
				// wird es nicht eingefügt (Duplikate)
				return;
			else if(entityArray[i] == null && index == -1)
				// Wenn die Stelle im Array leer ist und wir noch
				// keine Stelle gefunden haben,merken wir uns diese.
				index = i;
				
		// Falls gar keine Stelle gefunden wurde, vergrößern wir das Array und
		// fügen dann an dieser Stelle ein
		if (index == -1) {
			index = entityArray.length;
			resize();
		}
		
		// Einfügen
		entityArray[index] = e;
		
	}
	
	private void resize() {
		Entity[] newEntityArray = new Entity[entityArray.length*2];
		
		for (int i = 0; i < entityArray.length; i++)
			newEntityArray[i] = entityArray[i];
		
		entityArray = newEntityArray;
	}
	
	public void remove(Entity e) {
		for(int i = 0; i < entityArray.length; i++)
			if(entityArray[i] == e) {
				entityArray[i] = null;
				// Nachdem ersten Gelöschten Element kann aufgehört werden,
				// da wir keine Duplikate haben
				break;
			}
	}
	
	public String toString() {
		int n = 1;
		String s = "Content of set: ";
		for(int i = 0; i < entityArray.length; i++)
			if(entityArray[i] != null) {
				s += "\n\t" + n++ + ": " + entityArray[i];
			}
		return s;
	}
	
	public void nextStep() {
		for(int i = 0; i < entityArray.length; i++) {
			Entity e = entityArray[i];
			if(e != null) {
				e.nextStep();
				
				// Falls e ein Squirrel ist, dann muss auf Kollision geprüft werden
				if (e instanceof Squirrel) {
					checkCollisionWithGoodPlant((Squirrel) e);
				}
			}
		}
	}

	private void checkCollisionWithGoodPlant(Squirrel s) {
		for (Entity e2 : entityArray) {
			if (e2 instanceof GoodPlant && e2.getXY().equals(s.getXY())) {
				s.updateEnergy(e2.getEnergy());
				remove(e2);
			}
		}
	}
}
