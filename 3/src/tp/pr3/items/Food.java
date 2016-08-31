package tp.pr3.items;
import tp.pr3.Constants;
import tp.pr3.Player;
import tp.pr3.Room;

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
			System.out.println(Constants.MESSAGE_CHANGES);
			who.addHealth(this.givesHealth);
			this.setTimes(this.getTimes() - 1);
			if (this.getTimes() <= 0){ //Deletes the item if it can´t be used anymore
				if(who.removeItem(this.getId()))
					System.out.println("The " + this.getId() + " is empty. It is deleted from the inventory.");
			}
		}
		else{ //If it can´t be used it is deleted
			if(who.removeItem(this.getId()))
				System.out.println("The " + this.getId() + " is empty. It is deleted from the inventory.");
		}
		
		return used;
	}
	
}
