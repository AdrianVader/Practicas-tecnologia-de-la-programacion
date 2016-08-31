package tp.pr5;

import tp.pr5.items.Item;

public interface PlayerObserver {

	//Notifies that the player inventory has changed
	public void inventoryUpdate(java.util.List<Item> inventory);
	 
	//Notifies that an item is empty
	public void	itemEmpty(java.lang.String itemName);
	
	//Notifies that the player wants to examine an item
	public void itemLooked(java.lang.String description);
	
	//Notifies that an item has been used
	public void itemUsed(java.lang.String itemName);
	
	//Notifies the player's death
	public void playerDead();
	
	//Notifies that the player looked the inventory
	public void	playerLookedInventory(java.util.List<Item> inventory);
	
	//Notifies that the player attributes (health and score) have changed 
	public void	playerUpdate(int newHealth, int newScore);
	
}
