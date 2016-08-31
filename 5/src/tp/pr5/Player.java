package tp.pr5;

import java.util.ArrayList;

import tp.pr5.gui.PlayerPanel;
import tp.pr5.items.Item;

public class Player extends tp.pr5.Observable<PlayerObserver>{	//tiene un array de playerObservers

	private int health;
	private int score;
	private ArrayList<Item> inventory;
	@SuppressWarnings("unused")
	private PlayerPanel playerPanel;

	
	
	//Constructor of player.
	public Player(){
		
		super();
		this.health = Constants.INITIAL_LIVE;
		this.score = Constants.INITIAL_SCORE;
		this.inventory = new ArrayList<Item>();
	}
	
	
	
	//Add an item to the inventory
	public boolean addItem(Item item){
		
		boolean added = false, encontrado = false;
		for (int i = 0;i < this.inventory.size(); i++){
			if (this.inventory.get(i).getId().equalsIgnoreCase(item.getId()))// MEGA-PROBLEMA SI ESCRIBEs EN MAYÚSCULAS !!!
				encontrado = true;
			
		}
		if (!encontrado){
			Item copy = item;
			copy.setId(item.getId());
			this.inventory.add(copy);
			/*for(int j = 0; j < this.observadores.size();j++){
				this.observadores.get(j).inventoryUpdate(this.inventory);
			}*/
			added = true;
		}
		
		return added;
	}
	
	
	//Returns the item from the inventory according to the item name It returns the Item.
	public Item getItem(java.lang.String id){
		
		Item found = null;

		for (int i = 0;i < this.inventory.size(); i++){
			if (this.inventory.get(i).getId().equals(id)){
				found = this.inventory.get(i);
			}
		}		
		return found;		
	}
	
	
	//Delete a given item from the inventory.
	public boolean removeItem(java.lang.String id){
		
		boolean borrado = false;
		for (int i = 0;i < this.inventory.size(); i++){
			if (this.inventory.get(i).getId().equalsIgnoreCase(id)){
				this.inventory.remove(i);
				for(int j = 0; j < this.observadores.size();j++){
					this.observadores.get(j).inventoryUpdate(this.inventory);
				}
				borrado = true;
			}
		}
		return borrado;
	}
	
	
	//Checks if the player is dead.
	public boolean dead(){
		if(this.health <= 0){
			for(int i = 0; i < this.observadores.size();i++){
				this.observadores.get(i).playerDead();
			}
		}
		return (this.health <= 0);
	}
	
	
	//Returns the player scoring
	public int getPoints(){
		return this.score;
	}
	
	
	//Sum the value of points.
	public void addPoints(int points){
		this.score += points;
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).playerUpdate(this.health, this.score);
		}
	}
	
	
	//Returns the player health
	public int getHealth(){		
		return this.health;
	}
	
	
	//The health is updated.
	public void addHealth(int var_health){
		
		if (this.health + var_health > 0){ //If this.health = 0, the player is dead.
			this.health += var_health;  
		}
		else
			this.health = 0;
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).playerUpdate(this.health, this.score);
		}
	}
	
	
	//Shows the items carried by the player.
	public java.lang.String showItems(){
		java.lang.String info = "";
		
		if(this.inventory.size() <= 0){
			info = Constants.MESSAGE_POOR;
		}else{
			System.out.println("My items are: ");
			for (int i = 0; i < this.inventory.size(); i++){
				info = this.inventory.get(i).toString();
			}
		}
		return info;
	}
	
	//Returns the following message: "HEALTH = {HEALTH}, SCORE ={SCORE}" where {HEALTH} is the health of the user, and {SCORE} the score
	public java.lang.String toString(){
		
		return "HEALTH = " + this.health + ", SCORE = " + this.score;
	}

	
	//Requests the player to inform the observers that an item is empty
	public void itemEmpty(java.lang.String itemName){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).itemEmpty(itemName);
		}
	}
	
	//Requests the player to inform the observers about an item has been used
	public void itemUsed(java.lang.String itemName){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).itemUsed(itemName);
		}
	}
	
	//Requests the player to inform the observers about an item description
	public void lookItem(java.lang.String itemName){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).itemLooked(itemName);
		}
	}
	
	//Requests the player to inform the observers that the inventory has changed
	public void requestInventoryUpdate(){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).inventoryUpdate(this.inventory);
		}
	}
	 
	//Requests the player to inform the observers about the inventory description
	public void requestLookInventory(){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).playerLookedInventory(this.inventory);
		}
	}
	
	//Requests the player to inform the observers that the player attributes have changed 
	public void requestPlayerUpdate(){
		for(int i = 0;i < this.observadores.size();i++){
			this.observadores.get(i).playerUpdate(this.health, this.score);
		}
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	//Establishes the panel where the player will update its information on the Swing interface
	public void setPlayerPanel(PlayerPanel playerPanel){		
		this.playerPanel = playerPanel;
	}
	
	//Set the ArrayList of PlayerObservers.
	public ArrayList<PlayerObserver> getObservadores(){
		return this.observadores;
	}
	
	//Get the ArrayList of PlayerObservers.
	public void setObservadores(ArrayList<PlayerObserver> obs){
		this.observadores = obs;
	}
	
	
}
