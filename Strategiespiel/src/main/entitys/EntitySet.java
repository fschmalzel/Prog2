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
		for(int i = 0; i < entityArray.length; i++)
			if(entityArray[i] == null) {
				entityArray[i] = e;
				break;
			}
	}
	
	public void remove(Entity e) {
		for(int i = 0; i < entityArray.length; i++)
			if(entityArray[i] == e) {
				entityArray[i] = null;
				break;
			}
	}
	
	public String toString(){
		String s = "Content of set: ";
		for(int i = 0; i < entityArray.length; i++)
			if(entityArray[i] != null) {
				s += "\n" + entityArray[i];
			}
		return s;
	}
	
	public void nextStep() {
		for(int i = 0; i < entityArray.length; i++)
			if(entityArray[i] != null) {
				entityArray[i].nextStep();
			}
	}
}
