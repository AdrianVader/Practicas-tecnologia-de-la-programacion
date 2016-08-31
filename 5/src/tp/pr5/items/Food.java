package tp.pr5.items;

import tp.pr5.Player;
import tp.pr5.Room;

public class Food extends ExpirationItem{

	private int givesHealth;
	
	// Constructors
	
	//Constructor for food
	public Food(java.lang.String name, java.lang.String description, int health, int times){
		
		super(name, description, times);
		this.givesHealth = health;
	}
	
	//Constructor of a kind of food that is consumed in just one use.
	public Food(java.lang.String name, java.lang.String description, int health){
		
		super(name, description);
		this.givesHealth = health;
	}
		
	
	// Methods
	
	
	//Returns true if the player can use this object in this room. Food can be used in any place.
	@Override
	public boolean use(Player who, Room where){
		boolean used = false;
		if (this.canBeUsed()){ // times > 0
			used = true;
			who.addHealth(this.givesHealth);
			this.setTimes(this.getTimes() - 1);
			if (this.getTimes() <= 0){ //Deletes the item if it can´t be used anymore
				who.removeItem(this.getId());
			}
		}
		else{ //If it can´t be used it is deleted
			who.removeItem(this.getId());
		}
		
		return used;
	}

	
	public int getGivesHealth() {
		return givesHealth;
	}
	public void setGivesHealth(int givesHealth) {
		this.givesHealth = givesHealth;
	}
	
	
}
