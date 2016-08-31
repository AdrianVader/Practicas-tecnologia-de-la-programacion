package tp.pr5;

import java.util.ArrayList;

import tp.pr5.items.Item;

public class Room implements RoomInfo{

	private boolean exit;
	private java.lang.String description;
	private String name;
	private ArrayList<Item> items;
	
	
	
	//Constructors.
	
	//Initially, the room is created without items.
	public Room(boolean var_exit, java.lang.String var_description){
		this.name = "";
		this.exit = var_exit;
		this.items = new ArrayList<Item>();
		this.description = var_description;
	}
	
	//A room created with a set of items.
	public Room(boolean var_exit, java.lang.String var_description, Item[] var_items){
		this.name = "";
		this.exit = var_exit;
		this.items = new ArrayList<Item>();
		this.description = var_description;
		
		for(int i = 0; i < var_items.length; i++){// Me tengo que preocupar en la constructora de que no hay duplicados !
		
			if(!this.items.contains(var_items[i]))
				this.items.add(var_items[i]);
		
		}
		
	}
	
	//Initially, the room is created without items.
	public Room(boolean var_exit, java.lang.String var_description, java.lang.String var_name){
		this.name = var_name;
		this.exit = var_exit;
		this.items = new ArrayList<Item>();
		this.description = var_description;
	}
	
	
	
	
	
	//Methods.
	
	//Add an item to the items array that already exists 
	public boolean addItem(Item item){
		boolean added = false, encontrado = false;
		
		for(int i = 0;i < this.items.size(); i++){
			if(this.items.get(i).getId().equalsIgnoreCase(item.getId()))
				encontrado = true;
						
		}
		
		if(!encontrado){
			this.items.add(item);
			added = true;
		}
		
		return added;
	}	
	
	//Is it an exit room?
	public boolean isExit() {
		return this.exit;
	}

	//Sets the value of exit
	public void setExit(boolean var_exit) {
		this.exit = var_exit;
	}

	//Returns the room description
	public java.lang.String getDescription() {
		java.lang.String newDescription = this.description;
		
		if(this.items.size() <= 0)
			newDescription = newDescription + Constants.LINE_SEPARATOR + Constants.MESSAGE_NO_ITEMS;
		else{
			
			newDescription = newDescription + Constants.LINE_SEPARATOR + Constants.MESSAGE_ITEMS_ROOM;
			for (int i = 0; i < this.items.size(); i++)
					newDescription = newDescription + Constants.LINE_SEPARATOR + this.items.get(i).toString();
			
			
		}
		
		return newDescription;
	}
	
	//Sets the description
	public void setDescription(java.lang.String var_description) {
		this.description = var_description;
	}

	//Return true if the Item <> exists.
	public boolean existsItem(java.lang.String id){
		boolean encontrado = false;
		
		for(int i = 0;i < this.items.size(); i++){
			if(this.items.get(i).getId().toLowerCase().equals(id.toLowerCase())){
				encontrado = true;
			}
		}
		
		return encontrado;
	}	
	
	//Pick an item from the room and add it to the player's inventory.
	public boolean pickItem(Player who, java.lang.String id){
		boolean ok = false;
		
		for(int i = 0; i < this.items.size(); i++){
			if(this.items.get(i).getId().toLowerCase().equals(id.toLowerCase())){
				
				ok = who.addItem(this.items.get(i)); 
				
				if(ok)
					this.items.remove(i);
			}
		}
		
		return ok;
	}
	
	
	
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}