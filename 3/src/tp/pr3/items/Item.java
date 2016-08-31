package tp.pr3.items;
import tp.pr3.Room;
import tp.pr3.Player;

public abstract class Item {

	private String id;
	private String description;
	
	
	
	//Constructor for an item with a unique id and a description
	protected Item(java.lang.String var_id, java.lang.String var_description){
		
		this.id = var_id;
		this.description = var_description;
	}
	
	
	//Depending on the type of the item, it could be used forever (a key) or just a limited number of times (food and valuable).
	public abstract boolean canBeUsed();
	
	
	//Method that returns a string with information about an Item.
	@Override
	public String toString(){
		
		String info;
		info = "--item[" + this.id + "]=" + this.description;
		return info;
	}
	
	
	//Method called when using the item.
	public abstract boolean use(Player who, Room where);


	public String getDescription() {
		return description;
	}


	public void setDescription(String var_description) {
		this.description = var_description;
	}


	public void setId(String var_id) {
		this.id = var_id;
	}
	
	
	
	public int getTimes(){
		return -1;
	}
		
	
	//Returns the item name (or ID)
	public String getId(){		
		return this.id;
	}
	
}

