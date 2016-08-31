package tp.pr3;

import java.util.ArrayList;
import tp.pr3.items.Item;

public class Player {

	private int health;
	private int score;
	private ArrayList<Item> inventory;

	
	
	//Constructor of player.
	public Player(){
		
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
				borrado = true;
			}
		}
		return borrado;
	}
	
	
	//Checks if the player is dead.
	public boolean dead(){
		return (this.health <= 0);
	}
	
	
	//Returns the player scoring
	public int getPoints(){
		
		return this.score;
	}
	
	
	//Sum the value of points.
	public void addPoints(int points){
		
		this.score += points;
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
	}
	
	
	//Shows the items carried by the player.
	public java.lang.String showItems(){
		java.lang.String info = "";
		
		if(this.inventory.size() <= 0){
			info = Constants.MESAGE_POOR;
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
		
		return "HEALTH =" + this.health + ", SCORE =" + this.score;
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
	
}
