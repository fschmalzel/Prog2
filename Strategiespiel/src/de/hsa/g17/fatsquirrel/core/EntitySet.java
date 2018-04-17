package de.hsa.g17.fatsquirrel.core;

import de.hsa.g17.fatsquirrel.entities.Character;

public class EntitySet {

	private Entity[] entityArray;
	private static final int DEFAULT_SIZE = 50;
	private int size = 0;
	
	public EntitySet(int size) {
		entityArray = new Entity[size];
	}
	
	public EntitySet() {
		this(DEFAULT_SIZE);
	}

	public void insert(Entity e) {
		
		if (e == null)
			return;
		
		// Stelle an der eingefügt wird
		int index = -1;
		
		for(int i = 0; i < entityArray.length; i++)
			if(e.equals(entityArray[i]))
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
		size++;
	}
	
	private void resize() {
		Entity[] newEntityArray = new Entity[entityArray.length*2];
		
		for (int i = 0; i < entityArray.length; i++)
			newEntityArray[i] = entityArray[i];
		
		entityArray = newEntityArray;
	}
	
	public void remove(Entity e) {
		if (e == null)
			return;
		
		for(int i = 0; i < entityArray.length; i++)
			if(e.equals(entityArray[i])) {
				entityArray[i] = null;
				size--;
				// Nachdem ersten gelöschten Element kann aufgehört werden,
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
	
	public void nextStep(EntityContext context) {
		for(int i = 0; i < entityArray.length; i++) {
			Entity e = entityArray[i];
			if(e != null && e instanceof Character) {
				((Character) e).nextStep(context);
			}
		}
	}
	
	public int size() {
		return size;
	}
	
	public Entity[] toArray() {
		Entity[] copy = new Entity[size];
		
		int index = 0;
		
		for (Entity e : entityArray) {
			if (e != null) {
				copy[index++] = e;
			}
		}
		
		return copy;
		
	}
}
